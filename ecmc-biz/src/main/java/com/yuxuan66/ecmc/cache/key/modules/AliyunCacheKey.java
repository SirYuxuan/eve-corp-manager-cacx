package com.yuxuan66.ecmc.cache.key.modules;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
public interface AliyunCacheKey {

    /**
     * 阿里云OSS的地域
     */
    String OSS_ENDPOINT = "aliyun.oss.endpoint";

    /**
     * 阿里云OSS的bucket
     */
    String OSS_BUCKET = "aliyun.oss.bucket";
    /**
     * 阿里云密钥id
     */
    String ACCESS_KEY_ID = "aliyun.access.key";
    /**
     * 阿里云密码值
     */
    String ACCESS_KEY_SECRET = "aliyun.access.secret";
}
