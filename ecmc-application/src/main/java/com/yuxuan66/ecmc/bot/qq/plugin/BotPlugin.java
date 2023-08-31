package com.yuxuan66.ecmc.bot.qq.plugin;

import net.mamoe.mirai.event.events.MessageEvent;

/**
 * @author Sir丶雨轩
 * @since 2023/4/1
 */
public interface BotPlugin {

    /**
     * 是否支持私聊使用
     * @return 是否支持
     */
    default boolean isPrivate(){
        return true;
    }

    /**
     * 收到消息并处理
     * @param event 消息事件
     * @return 是否继续处理
     */
    boolean handleMessage(MessageEvent event);

}
