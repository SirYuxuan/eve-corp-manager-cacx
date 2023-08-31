package com.yuxuan66.ecmc.bot.qq.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxuan66.ecmc.bot.qq.service.entity.price.PriceBean;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.cache.mapper.TypeAliasMapper;
import com.yuxuan66.ecmc.cache.redis.RedisKit;
import com.yuxuan66.ecmc.common.upload.UploadContext;
import com.yuxuan66.ecmc.common.utils.Lang;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.MessageEvent;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Sir丶雨轩
 * @since 2023/1/12
 */
@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class EVEService {

    private final EveCache eveCache;
    private List<Type> typeList;
    private final RedisKit redis;
    private final UploadContext uploadContext;
    @Resource
    private TypeAliasMapper typeAliasMapper;


    /**
     * 获取一个名字的别名
     *
     * @param name 名称
     * @return 别名
     */
    public String getAlias(String name) {
        if (redis.hExist(CacheKey.EVE_TYPE_ALIAS, name)) {
            return redis.hGet(CacheKey.EVE_TYPE_ALIAS, name);
        }
        return name;
    }

    /**
     * 判断物品是否有效
     *
     * @param isSkin      是否是涂装
     * @param isBluePrint 是否是蓝图
     * @param type        物品
     * @return 是否有效
     */
    private boolean checkValidType(boolean isSkin, boolean isBluePrint, Type type) {
        if (!isBluePrint && "蓝图".equals(type.getCategoryName())) {
            return false;
        }
        return isSkin || !"涂装".equals(type.getCategoryName());
    }

    /**
     * 判断物品名称是否有效
     *
     * @param name 名称
     * @param type 物品
     * @return 是否有效
     */
    private boolean checkNameType(String name, Type type) {
        return type.getName().contains(name) || type.getNameEn().equalsIgnoreCase(name);
    }

    /**
     * 处理价格查询并发送至频道
     *
     * @param event 事件
     * @param name  查询的物品名称
     * @param isEur 是否是欧服
     * @param isCol 是否是全部
     */
    public void price(MessageEvent event, String name, boolean isEur, boolean isCol) {
        // 总耗时计时器
        TimeInterval intervalTotal = DateUtil.timer();

        // 1. 开始尝试获取别名
        TimeInterval intervalTemp = DateUtil.timer();
        final String realName = getAlias(name);
        final String pinyinName = PinyinUtil.getPinyin(realName) + " ";
        log.info("获取别名完成,耗时: " + intervalTemp.intervalPretty());

        // 2. 过滤出合适的物品列表
        intervalTemp = DateUtil.timer();
        boolean isSkin = name.contains("皮肤") || name.contains("涂装");
        boolean isBluePrint = name.contains("蓝图") || name.contains("配方");

        // 2.1 过滤出有效的TypeList
        List<Type> validList = typeList.stream().filter(item -> checkValidType(isSkin, isBluePrint, item)).toList();
        // 2.2 过滤出名字正确的TypeList
        List<Type> list = validList.stream().filter(item -> checkNameType(realName, item)).limit(10).toList();
        // 2.3 如果没有匹配到物品，尝试使用拼音查询
        if (list.isEmpty()) {
            list = validList.stream().filter(item -> item.getPinyin().contains(pinyinName)).limit(10).toList();
        }
        // 2.4 最终也无法匹配到名称
        log.info("名称检索完成,耗时: " + intervalTemp.intervalPretty());
        if (list.isEmpty()) {
            event.getSubject().sendMessage("你在查个嘚儿");
            return;
        }
        // 3. 开始获取价格
        intervalTemp = DateUtil.timer();
        Map<Integer, PriceBean> priceBeanMap = getPrice(isEur, list.stream().map(Type::getId).toList());
        log.info("价格获取完成,耗时: " + intervalTemp.intervalPretty());

        long sellAll = 0, buyAll = 0;

        StringBuilder result = new StringBuilder();
        // 5. 开始拼装数据
        for (Type type : list) {
            PriceBean priceBean = priceBeanMap.get(type.getId());
            sellAll += priceBean.getSell();
            buyAll += priceBean.getBuy();
            result.append(getName(type));
            result.append(getPriceVal(priceBean, 1));
            if ("伊甸币".equals(type.getName())) {
                result.append(getName(type).replace("\r", "")).append("*500\r").append(getPriceVal(priceBean, 500));
            }
        }
        if (isCol) {
            result.append(name).append("全套价格\r");
            result.append(getPriceVal(buyAll, sellAll));
        }
        event.getSubject().sendMessage(result.toString());
    }

    /**
     * 获取价格的输出
     *
     * @param priceBean 价格
     * @return 输出
     */
    public String getPriceVal(PriceBean priceBean, int multiple) {
        return "收单: " + Lang.getFormatNumber(priceBean.getBuy() * multiple) + " ISK \r中间: " + Lang.getFormatNumber((priceBean.getSell() + priceBean.getBuy()) / 2 * multiple) + " ISK\r卖单: " + Lang.getFormatNumber(priceBean.getSell() * multiple) + " ISK\r";
    }

    /**
     * 获取价格的输出
     *
     * @return 输出
     */
    public String getPriceVal(long buy, long sell) {
        return getPriceVal(new PriceBean(0, buy, sell), 1);
    }

    /**
     * 获取名字的格式化输出
     *
     * @param type 物品
     * @return 格式化输出
     */
    public String getName(Type type) {
        return type.getName() + "\r";
    }


    /***
     * 获取指定服务器物品的价格
     * @param isEur 是否是欧服
     * @param typeIdList 物品ID
     * @return 价格
     */
    /*public Map<Integer, PriceBean> getPrice(boolean isEur, List<Integer> typeIdList) {
        String basePath = "https://api.evemarketer.com/ec/marketstat/json?typeid=" + typeIdList.stream().map(Object::toString).collect(Collectors.joining(",")) + "&regionlimit=10000002&usesystem=30000142";
        JSONArray jsonArr = JSONArray.parseArray(HttpUtil.get(basePath));
        Map<Integer, PriceBean> result = new HashMap<>();
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject data = jsonArr.getJSONObject(i);
            int typeId = typeIdList.get(i);

            result.put(typeId, new PriceBean(typeId, data.getJSONObject("buy").getLongValue("max"),data.getJSONObject("sell").getLongValue("min")));
        }
        return result;
    }*/
    public Map<Integer, PriceBean> getPrice(boolean isEur, List<Integer> typeIdList) {
        StringBuilder basePath = new StringBuilder("http://www.ceve-market.org/" + (isEur ? "tq" : "") + "api/marketstat?usesystem=30000142");
        typeIdList.forEach(typeId -> basePath.append("&typeid=").append(typeId));
        HttpRequest request = HttpUtil.createGet(basePath.toString());
        request.setHttpProxy("47.99.84.100",6669);
        String xmlData = request.execute().body();
        NodeList nodeList = XmlUtil.parseXml(xmlData).getDocumentElement().getElementsByTagName("marketstat").item(0).getChildNodes();
        Map<Integer, PriceBean> result = new HashMap<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            int typeId = Convert.toInt(item.getAttributes().getNamedItem("id").getNodeValue());
            Node buyNode = item.getChildNodes().item(0);
            Node sellNode = item.getChildNodes().item(1);
            result.put(typeId, new PriceBean(typeId, Convert.toLong(buyNode.getChildNodes().item(2).getTextContent()), Convert.toLong(sellNode.getChildNodes().item(3).getTextContent())));
        }
        return result;
    }


    public static void main(String[] args) {
        StringBuilder basePath = new StringBuilder("http://www.ceve-market.org/" + (true ? "tq" : "") + "api/marketstat?usesystem=30000142&typeid=1");
        HttpRequest request = HttpUtil.createGet(basePath.toString());
        request.setHttpProxy("47.99.84.100",6669);
        String xmlData = request.execute().body();
        NodeList nodeList = XmlUtil.parseXml(xmlData).getDocumentElement().getElementsByTagName("marketstat").item(0).getChildNodes();
        Map<Integer, PriceBean> result = new HashMap<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            int typeId = Convert.toInt(item.getAttributes().getNamedItem("id").getNodeValue());
            Node buyNode = item.getChildNodes().item(0);
            Node sellNode = item.getChildNodes().item(1);
            result.put(typeId, new PriceBean(typeId, Convert.toLong(buyNode.getChildNodes().item(2).getTextContent()), Convert.toLong(sellNode.getChildNodes().item(3).getTextContent())));
        }
    }


}














































































































































































































































































































































































































































































































































