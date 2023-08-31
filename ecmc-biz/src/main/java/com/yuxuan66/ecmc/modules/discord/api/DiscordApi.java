package com.yuxuan66.ecmc.modules.discord.api;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.yuxuan66.ecmc.modules.discord.api.entity.oauth.RefreshTokenRequest;
import com.yuxuan66.ecmc.modules.discord.api.entity.oauth.TokenRequest;
import com.yuxuan66.ecmc.modules.discord.api.entity.oauth.TokenResponse;
import com.yuxuan66.ecmc.modules.system.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sir丶雨轩
 * @since 2023/1/11
 */
@Service
public class DiscordApi {
    /**
     * 接口地址
     */
    private static final String API_ENDPOINT = "https://discord.com/api/v10";
    private static final Map<String, String> headers = new HashMap<>() {{
        put("Content-Type", "application/x-www-form-urlencoded");
    }};

    /**
     * 根据Code获取token信息
     *
     * @param code code
     * @return token信息
     */
    public TokenResponse getToken(String code) {
        HttpRequest request = HttpUtil.createPost(API_ENDPOINT + "/oauth2/token");
        request.headerMap(headers, true);
        request.body(new TokenRequest(code).toBody());
        HttpResponse response = request.execute();
        if (response.getStatus() == 200) {
            return JSON.parseObject(response.body(), TokenResponse.class);
        }
        return null;
    }

    /**
     * 刷新一个用户的Discord token
     *
     * @param user 用户
     * @return 刷新结果
     */
    public boolean refreshToken(User user) {
        if (user.getDiscordExpiresIn().getTime() > System.currentTimeMillis()) {
            return true;
        }
        HttpRequest request = HttpUtil.createPost(API_ENDPOINT + "/oauth2/token");
        request.headerMap(headers, true);
        request.body(new RefreshTokenRequest(user.getDiscordRefreshToken()).toBody());
        HttpResponse response = request.execute();
        if (response.getStatus() == 200) {
            TokenResponse tokenResponse = JSON.parseObject(response.body(), TokenResponse.class);
            user.setDiscordAccessToken(tokenResponse.getAccessToken());
            user.setDiscordRefreshToken(tokenResponse.getRefreshToken());
            user.setDiscordExpiresIn(DateUtil.offsetSecond(new Date(),tokenResponse.getExpiresIn()).toTimestamp());
            user.updateById();
            return true;
        }
        return false;
    }



}
