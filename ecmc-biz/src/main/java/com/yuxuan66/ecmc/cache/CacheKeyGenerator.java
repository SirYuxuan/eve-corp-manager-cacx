package com.yuxuan66.ecmc.cache;

import com.yuxuan66.ecmc.common.utils.StpUtil;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 定义一些缓存的key
 *
 * @author Sir丶雨轩
 * @since 2022/12/14
 */
@Configuration
public class CacheKeyGenerator {


    /**
     * 根据用户ID生成缓存Key
     * @return 用户ID缓存Key
     */
    @Bean("userIdKey")

    public KeyGenerator userIdKeyGenerator() {
        return (o, method, params) -> "User:" + StpUtil.getLoginId();
    }
}
