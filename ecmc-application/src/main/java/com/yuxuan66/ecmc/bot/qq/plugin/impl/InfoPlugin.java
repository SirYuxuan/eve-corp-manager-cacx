package com.yuxuan66.ecmc.bot.qq.plugin.impl;

import com.yuxuan66.ecmc.bot.qq.ReConst;
import com.yuxuan66.ecmc.bot.qq.plugin.definition.MessagePlugin;
import com.yuxuan66.ecmc.bot.qq.plugin.definition.PluginInfo;
import com.yuxuan66.ecmc.modules.account.service.OpenAccountApiService;
import com.yuxuan66.ecmc.support.exception.BizException;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.At;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir丶雨轩
 * @since 2023/2/9
 */
@Component
public class InfoPlugin implements MessagePlugin {


    private final LinkedBlockingQueue<MessageEvent> blockingQueue = new LinkedBlockingQueue<>();

    public InfoPlugin(OpenAccountApiService openAccountApiService) {
        new Thread(() -> {
            try {
                while (true) {
                    MessageEvent event = blockingQueue.take();
                   try{
                       event.getSubject().sendMessage(new At(event.getSender().getId()).plus(" ").plus(openAccountApiService.info(event.getSender().getId())));
                   }catch (Exception e){
                       event.getSubject().sendMessage(new At(event.getSender().getId()).plus(" ").plus(e.getMessage()));
                   }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

    @Override
    public Matcher match(String message) {
        return Pattern.compile(ReConst.INFO, Pattern.CASE_INSENSITIVE).matcher(message);
    }

    @Override
    public PluginInfo info() {
        return new PluginInfo("INFO查询", 2);
    }

    @Override
    public boolean apply(MessageEvent event, Matcher matcher, String message) {
        // 获取发言人对应的QQ
        //event.getSubject().sendMessage(new At(event.getSender().getId()).plus(" 您的INFO查询已经加入队列, 当前队列数量: " + blockingQueue.size()));
        blockingQueue.add(event);
        return false;
    }
}
