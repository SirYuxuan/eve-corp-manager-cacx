package com.yuxuan66.ecmc.common.upload;

import com.yuxuan66.ecmc.common.upload.entity.Attach;
import com.yuxuan66.ecmc.common.upload.enums.UploadType;

/**
 * 文件上传接口
 *
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
public interface Upload {

    /**
     * 格式化文件名
     *
     * @param originalFilename 原始文件
     * @return 新文件名
     */
    default String formatFileName(String originalFilename) {
        return originalFilename;
    }


    /**
     * 用来适配具体的上传器
     *
     * @param uploadType 上传类型
     * @return 是否匹配
     */
    default boolean checkUse(UploadType uploadType) {
        return false;
    }


    /**
     * 上传文件返回新的文件名
     *
     * @param fileBytes 文件数据
     * @param originalName 文件原始名称
     * @param md5 文件的md5
     * @return 新文件名
     */
    Attach upload(byte[] fileBytes,String originalName,String md5);
}
