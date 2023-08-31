package com.yuxuan66.discord.bot;

import com.yuxuan66.discord.bot.plugin.definition.MessagePlugin;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;

/**
 * Discord所有消息的分发器, 可以做一些拦截的操作
 *
 * @author Sir丶雨轩
 * @since 2023/1/12
 */
@Slf4j
@Component
public class DiscordMessageDispatch {

    /**
     * 消息分发
     *
     * @param event 消息
     */
    public void dispatch(MessageCreateEvent event) {
        // 不处理机器人发送的消息
        if (event.getMessageAuthor().isBotUser()) {
            return;
        }
        for (MessagePlugin plugin : DiscordBot.messagePluginMap.values()) {
            String messageContent = event.getMessageContent();
            Matcher matcher = plugin.match(messageContent);
            log.info("Plugin: {}, Message: {}" ,plugin.info(), messageContent);
            if (matcher.find()) {
                log.info("插件匹配成功[" + plugin.info() + "]");
                if (!plugin.apply(event, matcher, messageContent)) {
                    break;
                }
            }
        }
    }


}
