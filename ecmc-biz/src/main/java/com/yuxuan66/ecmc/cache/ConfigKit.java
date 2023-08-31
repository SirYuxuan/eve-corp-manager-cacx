package com.yuxuan66.ecmc.cache;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.modules.system.entity.Config;
import com.yuxuan66.ecmc.modules.system.mapper.ConfigMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Config的配套查询工具，使用缓存
 *
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Slf4j
public final class ConfigKit {


    /**
     * 根据缓存的Key获取内容
     *
     * @param key key
     * @return 缓存内容
     */
    public static String get(String key) {
        return get(key, false);
    }

    /**
     * 根据缓存的key获取内容，转换为指定类型
     *
     * @param key   key
     * @param clazz 数据类型
     * @param force 是否强制更新
     * @param <T>   数据类型
     * @return 缓存内容
     */
    public static <T> T get(String key, Class<T> clazz, boolean force) {
        Object val = get(key, force);
        if(val == null){
            return null;
        }
        return Convert.convert(clazz, val);
    }

    /**
     * 根据缓存的key获取内容，转换为指定类型
     *
     * @param key   key
     * @param clazz 数据类型
     * @param <T>   数据类型
     * @return 缓存内容
     */
    public static <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, false);
    }


    /**
     * 根据缓存的Key获取内容
     *
     * @param key   key
     * @param force 是否强制
     * @return 缓存内容
     */
    public static String get(String key, boolean force) {
        if (!force && StaticComp.redisKit.hExist(CacheKey.CONFIG, key)) {
            return StaticComp.redisKit.hGet(CacheKey.CONFIG, key);
        }
        ConfigMapper configMapper = StaticComp.configMapper;
        Config config = configMapper.selectOne(new QueryWrapper<Config>().eq("name", key));
        if (config == null) {
            return StrUtil.EMPTY;
        }
        StaticComp.redisKit.hSet(CacheKey.CONFIG, key, config.getValue());
        return config.getValue();
    }


}
