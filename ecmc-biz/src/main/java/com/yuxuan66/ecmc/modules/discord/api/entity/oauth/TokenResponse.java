package com.yuxuan66.ecmc.modules.discord.api.entity.oauth;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2023/1/11
 */
@Data
public class TokenResponse {

    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "expires_in")
    private int expiresIn;
    @JSONField(name = "refresh_token")
    private String refreshToken;
}
