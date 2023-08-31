package com.yuxuan66.ecmc.cache.key.modules;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
public interface UploadCacheKey {

    /**
     * 上传类型
     */
    String UPLOAD_TYPE = "upload.type";

    /**
     * 本地上传，保存目录
     */
    String LOCAL_PATH = "upload.local.path";
    /**
     * 本地上传保存地址
     */
    String LOCAL_URL = "upload.local.url";
}
