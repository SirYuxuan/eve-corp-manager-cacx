package com.yuxuan66.ecmc.bot.qq;

import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.yuxuan66.ecmc.bot.qq.listener.EcmcBotListener;
import com.yuxuan66.ecmc.bot.qq.plugin.definition.MessagePlugin;
import com.yuxuan66.ecmc.bot.qq.plugin.definition.PluginInfo;
import com.yuxuan66.ecmc.bot.qq.service.EVEService;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.cache.mapper.TypeAliasMapper;
import com.yuxuan66.ecmc.cache.redis.RedisKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * QQ机器人核心组件
 *
 * @author Sir丶雨轩
 * @since 2023/4/1
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class QQBotCore implements ApplicationListener<ApplicationReadyEvent> {

    public static Bot botCore;
    private final EVEService eveService;
    private final EveCache eveCache;
    private final RedisKit redis;
    @Resource
    private TypeAliasMapper typeAliasMapper;

    /**
     * 系统内所有的消息插件
     */
    public static final Map<PluginInfo, MessagePlugin> messagePluginMap = new TreeMap<>(Comparator.comparingInt(PluginInfo::getSort));

    /**
     * SpringBoot启动完成
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        // try {

        //     List<Type> typeList = eveCache.getTypeList().stream().filter(item -> item.getMarketGroupId() != null).toList();
        //     for (Type type : typeList) {
        //         type.setPinyin(PinyinUtil.getPinyin(type.getName()));
        //     }
        //     eveService.setTypeList(typeList);
        //     // 处理别名缓存
        //     typeAliasMapper.selectList(null).forEach(item -> redis.hSet(CacheKey.EVE_TYPE_ALIAS, item.getName(), item.getAlias()));
        //     FixProtocolVersion.update();

        //     botCore = BotFactory.INSTANCE.newBot(2438372649L, "Yuanyuan2012!", botConfiguration -> {
        //         botConfiguration.fileBasedDeviceInfo();
        //         botConfiguration.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PAD);
        //     });
        //     botCore.login();
        //     if (botCore.isOnline()) {
        //         // 消息插件
        //         Map<String, MessagePlugin> messagePluginBeanMap = SpringUtil.getBeansOfType(MessagePlugin.class);
        //         messagePluginBeanMap.forEach((name, bean) -> messagePluginMap.put(bean.info(), bean));
        //         botCore.getEventChannel().subscribeAlways(MessageEvent.class, new EcmcBotListener());
        //     }
        // } catch (Exception e) {
        //     log.error("QQ机器人启动失败", e);
        // }
    }



}
