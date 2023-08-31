package com.yuxuan66.ecmc.bot.qq.listener;

import com.yuxuan66.ecmc.bot.qq.QQBotCore;
import com.yuxuan66.ecmc.bot.qq.plugin.definition.MessagePlugin;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;

import java.util.function.Consumer;
import java.util.regex.Matcher;

/**
 * @author Sir丶雨轩
 * @since 2023/4/1
 */
@Slf4j
public class EcmcBotListener implements Consumer<MessageEvent> {

    /**
     * 收到消息事件
     * @param messageEvent the input argument
     */
    @Override
    public void accept(MessageEvent messageEvent) {
        boolean isGroup = messageEvent instanceof GroupMessageEvent;
        for (MessagePlugin plugin : QQBotCore.messagePluginMap.values()) {
            String messageContent = messageEvent.getMessage().contentToString();
            Matcher matcher = plugin.match(messageContent);
            log.info("Plugin: {}, Message: {}" ,plugin.info(), messageContent);
            if (matcher.find()) {
                log.info("插件匹配成功[" + plugin.info() + "]");
               try{
                   if (!plugin.apply(messageEvent, matcher, messageContent)) {
                       break;
                   }
               }catch (Exception e){
                   messageEvent.getSender().sendMessage(e.getMessage());
               }
            }
        }

    }
}
