package com.yuxuan66.ecmc.common.upload.impl;

import cn.hutool.core.io.FileUtil;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.modules.UploadCacheKey;
import com.yuxuan66.ecmc.common.upload.AbstractUpload;
import com.yuxuan66.ecmc.common.upload.entity.Attach;
import com.yuxuan66.ecmc.common.upload.enums.UploadType;
import com.yuxuan66.ecmc.support.exception.BizException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
@Component
public class LocalUpload extends AbstractUpload {

    /**
     * 判断是否使用此上传器
     *
     * @param uploadType 上传类型
     * @return 是否匹配
     */
    @Override
    public boolean checkUse(UploadType uploadType) {
        return uploadType.equals(UploadType.LOCAL);
    }

    /**
     * 获得并校验对应文件夹是否存在
     *
     * @return 保存目录
     */
    private Path getAndCheckPath() {
        String savePath = ConfigKit.get(UploadCacheKey.LOCAL_PATH);

        if (!savePath.endsWith(File.separator)) {
            savePath += File.separator;
        }
        String datePath = getDatePath();
        savePath += getDatePath() + File.separator;

        if (!FileUtil.exist(savePath)) {
            FileUtil.mkdir(savePath);
        }
        return new Path(datePath, savePath);
    }

    /**
     * 文件上传
     *
     * @param fileBytes    文件数据
     * @param originalName 文件原始名称
     * @param md5          文件md5
     * @return 上传后文件
     */
    @SneakyThrows
    @Override
    public Attach upload(byte[] fileBytes, String originalName, String md5) {

        // 获得并校验保存目录
        Path path = getAndCheckPath();

        // 格式化保存的文件名
        String fileName = formatFileName(originalName);
        String saveFilePath = path.getSavePath() + fileName;
        try {
            FileUtil.writeBytes(fileBytes, saveFilePath);

            String uploadUrl = ConfigKit.get(UploadCacheKey.LOCAL_URL);
            if (!uploadUrl.endsWith(File.separator)) {
                uploadUrl += File.separator;
            }
            Attach attach = new Attach();
            attach.setUploadType(UploadType.LOCAL);
            attach.setFilePath(path.getSavePath());
            attach.setUrl(uploadUrl + path.getDatePath() + File.separator + fileName);
            attach.setFileName(fileName);
            attach.setOriginalName(originalName);
            attach.setMd5(md5);
            return attach;
        } catch (Exception e) {
            throw new BizException("文件上传失败.", e);
        }
    }


    /**
     * 文件路径
     */
    @Data
    @AllArgsConstructor
    private static class Path {
        /**
         * 根据日期创建的目录
         */
        private String datePath;
        /**
         * 保存在硬盘的目录
         */
        private String savePath;
    }

}
