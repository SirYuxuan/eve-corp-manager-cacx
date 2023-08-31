package com.yuxuan66.ecmc.modules.bot.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.cache.redis.RedisKit;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;

/**
 * @author Sir丶雨轩
 * @since 2023/1/5
 */
@Slf4j
@Setter
@Service
@RequiredArgsConstructor
public class OpenBotService {

    private final EveCache eveCache;
    private final RedisKit redis;
    private List<Type> typeList;
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
     * 获取一个物品的价格
     * @param isEur 是否是欧服
     * @param isCol 是否是组查询
     * @param name 名称
     * @return 信息
     */
    public Object getPrice(boolean isEur,boolean isCol,String name) {
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
            return "你在查个嘚儿";
        }

        StringBuilder sendMessage = new StringBuilder();
        List<Integer> typeIds = new ArrayList<>();
        Map<String, Object> send = new HashMap<>();
        long allBuy = 0;
        long allSell = 0;
        Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("124.221.185.93",3128));
        for (int i = 0; i < list.size(); i++) {
            String typeName = list.get(i).getName();
            String typeId = list.get(i).getId().toString();

            HttpRequest request = HttpUtil.createGet(getPriceUrl(isEur) + "/marketstat?typeid=" + typeId + "&usesystem=30000142");
            request.setProxy(proxy);
            String itemStr =  request.execute().body();

            Document document = XmlUtil.parseXml(itemStr);
            Node buyNode = document.getDocumentElement().getFirstChild().getFirstChild().getChildNodes().item(0);
            Node sellNode = document.getDocumentElement().getFirstChild().getFirstChild().getChildNodes().item(1);
            String buyMax = buyNode.getChildNodes().item(2).getTextContent();
            String sellMin = sellNode.getChildNodes().item(3).getTextContent();

            sendMessage.append(typeName).append(" \r\n收单: ").append(NumberUtil.decimalFormat(",###", Convert.toLong(buyMax))).append(" ISK").append(" \r\n卖单: ").append(NumberUtil.decimalFormat(",###", Convert.toLong(sellMin))).append(" ISK\r\n=============\n");
            if (name.equals("伊甸币")) {
                sendMessage.append("500*伊甸币价格");
                sendMessage.append("\r\n收单: ").append(NumberUtil.decimalFormat(",###", Convert.toLong(buyMax) * 500)).append(" ISK");
                sendMessage.append("\r\n卖单: ").append(NumberUtil.decimalFormat(",###", Convert.toLong(sellMin) * 500)).append(" ISK");
                sendMessage.append("\r\n=============");
                send.put("buy500",NumberUtil.decimalFormat(",###", Convert.toLong(buyMax) * 500) + " ISK");
                send.put("sell500",NumberUtil.decimalFormat(",###", Convert.toLong(sellMin) * 500) + " ISK");
                send.put("split500",NumberUtil.decimalFormat(",###", ((Convert.toLong(buyMax)+Convert.toLong(sellMin))/2) * 500) + " ISK");

            }
            send.put("buy", NumberUtil.decimalFormat(",###", Convert.toLong(buyMax)) + " ISK");
            send.put("sell", NumberUtil.decimalFormat(",###", Convert.toLong(sellMin)) + " ISK");
            send.put("split", NumberUtil.decimalFormat(",###", (Convert.toLong(buyMax) + Convert.toLong(sellMin))/2) + " ISK");
            allBuy += Convert.toLong(buyMax);
            allSell += Convert.toLong(sellMin);
            typeIds.add(Integer.valueOf(typeId));
        }
        if (isCol) {
            sendMessage.append("全套收单: ").append(NumberUtil.decimalFormat(",###", allBuy)).append(" ISK");
            sendMessage.append("\r\n全套卖单: ").append(NumberUtil.decimalFormat(",###", allSell)).append(" ISK");
            sendMessage.append("\r\n全套中间价: ").append(NumberUtil.decimalFormat(",###", (allSell+allBuy) / 2)).append(" ISK");
            sendMessage.append("\r\n=============");

        }
        send.put("msg", sendMessage.toString());
        if (typeIds.size() == 1) {
            // 查询历史
            HttpRequest post = HttpUtil.createPost("https://janice.e-351.com/api/rpc/v1?m=Info.getMarketDetails");
            post.form("~request~", "{\"id\":1,\"method\":\"Info.getMarketDetails\",\"params\":{\"itemTypeEid\":" + typeIds.get(0) + ",\"marketId\":2}}");
            JSONObject object = JSONObject.parseObject(post.execute().body());
            send.put("typeId", typeIds.get(0));
            Optional<Type> type = eveCache.getTypeList().stream().filter(item -> item.getId().equals(typeIds.get(0))).findFirst();
            if (type.isPresent()) {
                send.put("typeName", type.get().getName());
                send.put("typeEnName", type.get().getNameEn());
            }

            List<Date> times = new ArrayList<>();
            List<BigDecimal> mins = new ArrayList<>();
            List<BigDecimal> maxs = new ArrayList<>();
            JSONObject priceJson = object.getJSONObject("result").getJSONObject("price");;
            for (Object o : object.getJSONObject("result").getJSONArray("openHistory")) {
                JSONObject temp = (JSONObject) o;
                times.add(temp.getDate("date"));
                mins.add(temp.getBigDecimal("buyPriceMin"));
                maxs.add(temp.getBigDecimal("sellPriceMax"));
            }
            send.put("buyPrice30DayMedianDelta",priceJson.getBigDecimal("buyPrice30DayMedianDelta").multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
            send.put("sellPrice30DayMedianDelta",priceJson.getBigDecimal("sellPrice30DayMedianDelta").multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
            send.put("splitPrice30DayMedianDelta",priceJson.getBigDecimal("splitPrice30DayMedianDelta").multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));


            send.put("buyPrice30DayMedian",NumberUtil.decimalFormat(",###", priceJson.getBigDecimal("buyPrice30DayMedian").setScale(2, RoundingMode.HALF_UP)));
            send.put("sellPrice30DayMedian",NumberUtil.decimalFormat(",###", priceJson.getBigDecimal("sellPrice30DayMedian").setScale(2, RoundingMode.HALF_UP)));
            send.put("splitPrice30DayMedian",NumberUtil.decimalFormat(",###", priceJson.getBigDecimal("splitPrice30DayMedian").setScale(2, RoundingMode.HALF_UP)));
            send.put("history", new HashMap<String, Object>() {{
                put("times", times);
                put("mins", mins);
                put("maxs", maxs);
            }});
        }

        return JSON.toJSONString(send);
    }

    /**
     * 获取价格查询的请求地址
     *
     * @param isEur 是否是欧服
     * @return 地址
     */
    private String getPriceUrl(boolean isEur) {
        return "https://www.ceve-market.org/" + (isEur ? "tqapi" : "api");
    }
}
