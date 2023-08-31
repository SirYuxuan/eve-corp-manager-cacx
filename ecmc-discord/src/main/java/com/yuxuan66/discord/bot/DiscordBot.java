package com.yuxuan66.discord.bot;

import cn.hutool.extra.spring.SpringUtil;
import com.yuxuan66.discord.bot.plugin.definition.MessagePlugin;
import com.yuxuan66.discord.bot.plugin.definition.PluginInfo;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Sir丶雨轩
 * @since 2023/1/12
 */
@Service
@DependsOn("staticComp")
@Getter
@RequiredArgsConstructor
public class DiscordBot {
    private DiscordApi discordApi;
    private final DiscordMessageDispatch discordMessageDispatch;
    /**
     * 系统内所有的消息插件
     */
    public static final Map<PluginInfo, MessagePlugin> messagePluginMap = new TreeMap<>(Comparator.comparingInt(PluginInfo::getSort));

    /**
     * 初始化并上线机器人
     */
    @PostConstruct
    public void initialization() {
        // 消息插件
        Map<String, MessagePlugin> messagePluginBeanMap = SpringUtil.getBeansOfType(MessagePlugin.class);
        messagePluginBeanMap.forEach((name, bean) -> messagePluginMap.put(bean.info(), bean));

        // 初始化所有插件
        discordApi = new DiscordApiBuilder()
                //.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1080)))
                .setToken(ConfigKit.get(CacheKey.BOT_TOKEN))
                .setAllIntents()
                .login()
                .join();
        // 添加消息监听, 分发消息事件
        discordApi.addMessageCreateListener(discordMessageDispatch::dispatch);
    }
}
