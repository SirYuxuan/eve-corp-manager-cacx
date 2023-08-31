package com.yuxuan66.ecmc.common.upload.impl;

import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.common.upload.AbstractUpload;
import com.yuxuan66.ecmc.common.upload.entity.Attach;
import com.yuxuan66.ecmc.common.upload.enums.UploadType;
import com.yuxuan66.ecmc.support.aliyun.oss.OssUtil;
import org.springframework.stereotype.Component;

/**
 * 阿里云文件上传
 *
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
@Component
public class AliOssUpload extends AbstractUpload {

    /**
     * 判断是否使用此上传器
     *
     * @param uploadType 上传类型
     * @return 是否匹配
     */
    @Override
    public boolean checkUse(UploadType uploadType) {
        return uploadType.equals(UploadType.ALI_OSS);
    }

    /**
     * 文件上传
     *
     * @param fileBytes    文件数据
     * @param originalName 文件原始名称
     * @param md5          文件md5
     * @return 上传后文件
     */
    @Override
    public Attach upload(byte[] fileBytes, String originalName, String md5) {
        String datePath = getDatePath() + "/";
        OssUtil.checkAndCreatePath(datePath);
        String fileName = formatFileName(originalName);
        OssUtil.upload(fileBytes, datePath + fileName);
        Attach attach = new Attach();
        attach.setUploadType(UploadType.ALI_OSS);
        attach.setFilePath(datePath);
        attach.setFileName(fileName);
        attach.setOriginalName(originalName);
        attach.setMd5(md5);
        attach.setUrl(getUrl(datePath + fileName));

        return attach;
    }

    /**
     * 获取一个文件的访问地址
     *
     * @param fileName 文件名
     * @return 访问地址
     */
    private String getUrl(String fileName) {
        String url = ConfigKit.get(CacheKey.OSS_ENDPOINT).replace("https://", "");
        return "https://" + ConfigKit.get(CacheKey.OSS_BUCKET) + "." + url + "/" + fileName;
    }
}
