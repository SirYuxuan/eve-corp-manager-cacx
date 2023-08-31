package com.yuxuan66.ecmc.cache.key;


import com.yuxuan66.ecmc.cache.key.modules.*;

/**
 * 缓存Key
 *
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public interface CacheKey extends UploadCacheKey, AliyunCacheKey, EveCacheKey, WebCacheKey, MailCacheKey,DiscordCacheKey {


    /**
     * 系统核心配置Key
     */
    String CONFIG = "SYS_CONFIG";
    /**
     * 物品物品别名
     */
    String EVE_TYPE_ALIAS = "EVE_TYPE_ALIAS";


    /**
     * 邮件code保存
     */
    String EMAIL_CODE = "SYSTEM:CODE:EMAIL";
    /**
     * 重置密码使用
     */
    String EMAIL_CODE_RESET = "SYSTEM:CODE:EMAIL:RESET";


    /**
     * 联盟通讯Cookie
     */
    String SEAT_COOKIE = "seat.cookie";
    /**
     * 模板哭了
     */
    String TEMPLATE_PATH = "template.path";
}
