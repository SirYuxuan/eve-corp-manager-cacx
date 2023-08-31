package com.yuxuan66.ecmc.modules.discord.api.forest;

import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.lifecycles.MethodAnnotationLifeCycle;
import com.dtflys.forest.reflection.ForestMethod;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.modules.discord.api.forest.annotation.BotToken;

/**
 * @author Sir丶雨轩
 * @since 2023/1/12
 */
public class BotTokenLifeCycle implements MethodAnnotationLifeCycle<BotToken, Object> {

    @Override
    public boolean beforeExecute(ForestRequest request) {
        request.addHeader("Authorization", "Bot " + ConfigKit.get(CacheKey.BOT_TOKEN));
        request.addHeader("User-Agent","DiscordBot ");
        request.addHeader("Content-Type","application/json");
        return true;
    }

    @Override
    public void onMethodInitialized(ForestMethod method, BotToken annotation) {

    }
}
