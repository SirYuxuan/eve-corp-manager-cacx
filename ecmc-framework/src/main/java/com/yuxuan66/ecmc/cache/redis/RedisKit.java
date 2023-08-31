package com.yuxuan66.ecmc.cache.redis;

import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作工具
 *
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Component
public class RedisKit {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 判断一组key是否存在
     *
     * @param keys key列表
     * @return 是否存在
     */
    public boolean exist(String... keys) {
        for (String key : keys) {
            if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个缓存的Hash对象的key否存在
     *
     * @param key  缓存key
     * @param hKey hashKey
     * @return 是否存在
     */
    public boolean hExist(String key, String hKey) {
        if (!exist(key)) {
            return false;
        }
        return redisTemplate.opsForHash().hasKey(key, hKey);
    }

    /**
     * 删除一组缓存
     *
     * @param keys key列表
     */
    public void del(String... keys) {
        redisTemplate.delete(Arrays.stream(keys).toList());
    }

    /**
     * 从缓存中获取字符串数据
     *
     * @param key key
     * @return 字符串数据
     */
    public String get(String key) {
        if (exist(key)) {
            return Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString();
        }
        return StrUtil.EMPTY;
    }

    /**
     * 从缓存中获取字符串数据后删除
     *
     * @param key key
     * @return 字符串数据
     */
    public String getAndDel(String key) {
        String value = get(key);
        del(key);
        return value;
    }

    /**
     * 从缓存的hash对象中获取指定key的数据
     *
     * @param key  缓存key
     * @param hKey hashKey
     * @return 数据
     */
    public String hGet(String key, String hKey) {
        if (hExist(key, hKey)) {
            return Objects.requireNonNull(redisTemplate.opsForHash().get(key, hKey)).toString();
        }
        return StrUtil.EMPTY;
    }
    /**
     * 从缓存的hash对象中获取指定key的数据并删除
     *
     * @param key  缓存key
     * @param hKey hashKey
     * @return 数据
     */
    public String hGetAndDel(String key, String hKey) {
        if (hExist(key, hKey)) {
            String val = Objects.requireNonNull(redisTemplate.opsForHash().get(key, hKey)).toString();
            hDel(key,hKey);
            return val;
        }
        return StrUtil.EMPTY;
    }

    /**
     * 设置字符串数据到缓存
     *
     * @param key  key
     * @param data 数据
     */
    public void set(String key, String data) {
        redisTemplate.opsForValue().set(key, data);
    }

    /**
     * 设置字符串数据到缓存，并设置过期时间
     *
     * @param key  key
     * @param data 数据
     * @param time 过期时间
     */
    public void set(String key, String data, long time) {
        redisTemplate.opsForValue().set(key, data);
        if (time > 0) {
            expire(time, key);
        }
    }

    /**
     * 设置字符串数据到hash缓存
     *
     * @param key  key
     * @param hKey hashKey
     * @param data 数据
     */
    public void hSet(String key, String hKey, String data) {
        redisTemplate.opsForHash().put(key, hKey, data);
    }

    /**
     * 删除hash中指定的key值
     * @param key 缓存key
     * @param hKey hash key
     */
    public void hDel(String key, String... hKey) {
        redisTemplate.opsForHash().delete(key, hKey);
    }


    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    public void expire(long time, String... key) {
        try {
            if (time > 0) {
                for (String s : key) {
                    redisTemplate.expire(s, time, TimeUnit.SECONDS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
