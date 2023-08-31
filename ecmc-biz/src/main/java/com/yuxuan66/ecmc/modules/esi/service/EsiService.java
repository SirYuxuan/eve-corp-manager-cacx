package com.yuxuan66.ecmc.modules.esi.service;

import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.common.esi.EsiHelper;
import com.yuxuan66.ecmc.common.utils.StpUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.troja.eve.esi.auth.SsoScopes;
import org.springframework.stereotype.Service;

/**
 * esi相关处理
 *
 * @author Sir丶雨轩
 * @since 2022/12/12
 */
@Service
@RequiredArgsConstructor
public class EsiService {



    /**
     * 构建ESI授权地址
     *
     * @param token 用户Token，此项不能为空
     * @return 授权登录地址
     */
    public String authPath(String token) {
        return EsiHelper.defaultOAuth().getAuthorizationUri(ConfigKit.get(CacheKey.EVE_ESI_CALLBACK_PATH), SsoScopes.ALL, StpUtil.getLoginId(token).toString());
    }

    /**
     * ESI授权回调
     *
     * @param code  esi code
     * @param state 用户token
     */
    @SneakyThrows
    public String callback(String code, String state) {
        return ConfigKit.get(CacheKey.WEB_SITE) + "esi/wait?code=" + code;
    }

}
