package com.yuxuan66.ecmc.modules.discord.api.entity.oauth;

import com.alibaba.fastjson2.annotation.JSONField;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.modules.discord.api.entity.BaseDiscordEntity;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2023/1/11
 */
@Data
public class TokenRequest extends BaseDiscordEntity {
    @JSONField(name = "client_id")
    private String clientId;
    @JSONField(name = "client_secret")
    private String clientSecret;
    @JSONField(name = "grant_type")
    private String grantType = "authorization_code";
    private String code;
    @JSONField(name = "redirect_uri")
    private String redirectUri;

    public TokenRequest(String code) {
        this.code = code;
        this.clientId = ConfigKit.get(CacheKey.CLIENT_ID);
        this.clientSecret = ConfigKit.get(CacheKey.CLIENT_SECRET);
        this.redirectUri = ConfigKit.get(CacheKey.REDIRECT_URI);
    }
}
