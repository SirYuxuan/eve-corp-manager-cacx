package com.yuxuan66.ecmc.common.esi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import lombok.SneakyThrows;
import net.troja.eve.esi.ApiCallback;
import net.troja.eve.esi.ApiClient;
import net.troja.eve.esi.ApiClientBuilder;
import net.troja.eve.esi.auth.JWT;
import net.troja.eve.esi.auth.OAuth;

import java.util.Base64;

/**
 * 用来动态创建一些组件
 *
 * @author Sir丶雨轩
 * @since 2022/12/14
 */
public class EsiHelper {

    /**
     * TODO 全局使用一个ApiClient可能有线程安全问题，需要后续测试
     * 默认的ApiClient
     */
    private static final ApiClient DEFAULT_API_CLIENT = newClient();
    /**
     * 默认的API回调实现
     */
    private static final ApiCallback<Integer> DEFAULT_API_CALLBACK = new DefalutApiCallback();

    /**
     * 默认的API回调实现
     * @return api
     */
    public static ApiCallback<Integer> defaultCallback(){
        return DEFAULT_API_CALLBACK;
    }

    /**
     * 返回默认的ESI客户端
     *
     * @return ESI客户端
     */
    public static ApiClient defaultClient() {
        return DEFAULT_API_CLIENT;
    }

    /**
     * 创建一个新的ESI客户端
     *
     * @return ESI客户端
     */
    public static ApiClient newClient() {
        return new ApiClientBuilder().clientID(ConfigKit.get(CacheKey.EVE_ESI_CLIENT_ID)).build();
    }

    /**
     * 用accessToken构建JWT
     * @param accessToken token信息
     * @return jwt
     */
    public static JWT getJWT(String accessToken){
        String[] parts = accessToken.split("\\.");
        int needLen = 3;
        if (parts.length != needLen) {
            return null;
        }
        Gson gson = new GsonBuilder().registerTypeAdapter(JWT.Payload.class, new JWT.PayloadDeserializer())
                .create();
        JWT.Header header = gson.fromJson(new String(Base64.getUrlDecoder().decode(parts[0])), JWT.Header.class);
        JWT.Payload payload = gson.fromJson(new String(Base64.getUrlDecoder().decode(parts[1])), JWT.Payload.class);
        String signature = parts[2];
        return new JWT(header, payload, signature);
    }

    /**
     * 获取一个ESI的OAuth对象
     *
     * @param apiClient ESI客户端
     * @return OAuth对象
     */
    public static OAuth defaultOAuth(ApiClient apiClient) {
        return (OAuth) apiClient.getAuthentication("evesso");
    }

    /**
     * 获取一个ESI的OAuth对象
     *
     * @return OAuth对象
     */
    public static OAuth defaultOAuth() {
        return defaultOAuth(defaultClient());
    }

    /**
     * 创建一个Api
     *
     * @param clazz api类型
     * @param client 用户客户端
     * @param <T>   api类型
     * @return api
     */
    @SneakyThrows
    public static <T> T createApi(Class<T> clazz, ApiClient client) {
        return clazz.getConstructor(ApiClient.class).newInstance(client);
    }

    /**
     * 创建一个Api
     *
     * @param clazz api类型
     * @param <T>   api类型
     * @return api
     */
    public static <T> T createApi(Class<T> clazz) {
        try {
            return clazz.getConstructor(ApiClient.class).newInstance(defaultClient());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
