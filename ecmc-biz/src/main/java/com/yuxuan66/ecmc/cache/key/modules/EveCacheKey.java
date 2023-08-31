package com.yuxuan66.ecmc.cache.key.modules;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
public interface EveCacheKey {


    //************************ EVE ESI配置key ***************************/

    /**
     * EVE ESI ClientID
     */
    String EVE_ESI_CLIENT_ID = "eve.esi.client";
    /**
     * EVE ESI secretKey
     */
    String EVE_ESI_SECRET_KEY = "eve.esi.secret";
    /**
     * EVE ESI 授权回调
     */
    String EVE_ESI_CALLBACK_PATH = "eve.es.callback";


    /**
     * EVE主军团id
     */
    String EVE_MAIN_CORP = "eve.main.corp";
}
