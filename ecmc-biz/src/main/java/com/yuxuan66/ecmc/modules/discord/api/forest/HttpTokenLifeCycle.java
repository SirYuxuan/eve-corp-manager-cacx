package com.yuxuan66.ecmc.modules.discord.api.forest;

import cn.hutool.extra.spring.SpringUtil;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.lifecycles.MethodAnnotationLifeCycle;
import com.dtflys.forest.reflection.ForestMethod;
import com.yuxuan66.ecmc.modules.discord.api.DiscordApi;
import com.yuxuan66.ecmc.modules.discord.api.forest.annotation.Token;
import com.yuxuan66.ecmc.modules.system.entity.User;

/**
 * @author Sir丶雨轩
 * @since 2023/1/11
 */
public class HttpTokenLifeCycle implements MethodAnnotationLifeCycle<Token, Object> {


    @Override
    public boolean beforeExecute(ForestRequest request) {
        Object object = request.argument(0);
        if (object instanceof User user) {
            DiscordApi discordApi = SpringUtil.getBean(DiscordApi.class);
            if(!discordApi.refreshToken(user)){
                return false;
            }
            request.addHeader("Authorization", "Bearer " + user.getDiscordAccessToken());
        }
        return true;
    }

    @Override
    public void onMethodInitialized(ForestMethod method, Token annotation) {

    }
}
