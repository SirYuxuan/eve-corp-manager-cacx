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
public class RefreshTokenRequest extends BaseDiscordEntity {
    @JSONField(name = "client_id")
    private String clientId;
    @JSONField(name = "client_secret")
    private String clientSecret;
    @JSONField(name = "grant_type")
    private String grantType = "refresh_token";
    @JSONField(name = "refresh_token")
    private String refreshToken;

    public RefreshTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
        this.clientId = ConfigKit.get(CacheKey.CLIENT_ID);
        this.clientSecret = ConfigKit.get(CacheKey.CLIENT_SECRET);
    }
}
