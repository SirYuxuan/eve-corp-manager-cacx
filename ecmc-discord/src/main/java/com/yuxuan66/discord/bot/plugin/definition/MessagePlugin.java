package com.yuxuan66.discord.bot.plugin.definition;

import org.javacord.api.event.message.MessageCreateEvent;

import java.util.regex.Matcher;

/**
 * Discord消息插件
 *
 * @author Sir丶雨轩
 * @since 2023/1/12
 */
public interface MessagePlugin {

    /**
     * 返回插件的信息
     *
     * @return 插件信息
     */
    default PluginInfo info() {
        return new PluginInfo("未知", 999);
    }


    /**
     * 是否匹配此插件
     *
     * @param message 消息
     * @return 是否匹配
     */
    Matcher match(String message);

    /**
     * 应用插件
     *
     * @param event 消息事件
     * @return 是否继续
     */
    boolean apply(MessageCreateEvent event, Matcher matcher, String message);
}
