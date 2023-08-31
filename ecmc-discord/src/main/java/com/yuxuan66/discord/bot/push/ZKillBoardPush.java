package com.yuxuan66.discord.bot.push;

import com.yuxuan66.discord.bot.DiscordBot;
import com.yuxuan66.ecmc.cache.EveCache;
import lombok.RequiredArgsConstructor;
import org.java_websocket.client.WebSocketClient;
import org.javacord.api.DiscordApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Sir丶雨轩
 * @since 2023/1/13
 */
@Configuration
@RequiredArgsConstructor
public class ZKillBoardPush {
    private final DiscordBot discordBot;
    private final EveCache eveCache;
    @Bean
    public WebSocketClient webServiceClient() {
        DiscordApi discordApi = discordBot.getDiscordApi();
        try {
            ZKillBordWebSocketClient client = new ZKillBordWebSocketClient(new URI("wss://zkillboard.com/websocket/"), discordApi, eveCache);
            client.connect();
            //如果断线，则重连并重新发送验证信息
            Timer t = new Timer();
            t.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (!ZKillBordWebSocketClient.WEB_STATUS) {
                        try {
                            ZKillBordWebSocketClient mWebSocketClient = new ZKillBordWebSocketClient(new URI("wss://zkillboard.com/websocket/"), discordApi, eveCache);
                            mWebSocketClient.connect();
                        } catch(URISyntaxException e){
                            e.printStackTrace();
                        }
                    }
                }
            },2000,5000);//5秒执行一次 然后休息2秒
            return client;
        } catch(URISyntaxException e){
            e.printStackTrace();
        }
        return null;
    }

}
