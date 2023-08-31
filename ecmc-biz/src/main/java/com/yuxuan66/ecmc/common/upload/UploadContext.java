package com.yuxuan66.ecmc.common.upload;

import cn.hutool.extra.spring.SpringUtil;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.modules.UploadCacheKey;
import com.yuxuan66.ecmc.common.upload.entity.Attach;
import com.yuxuan66.ecmc.common.upload.enums.UploadType;
import com.yuxuan66.ecmc.support.exception.BizException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 文件上传上下文
 *
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
@Component
public class UploadContext implements Upload {


    /**
     * 根据系统配置上传文件
     *
     * @param fileBytes    文件数据
     * @param originalName 文件原始名称
     * @return 文件
     */
    @Override
    public Attach upload(byte[] fileBytes, String originalName, String md5) {
        UploadType uploadType = UploadType.getByVal(ConfigKit.get(UploadCacheKey.UPLOAD_TYPE, Integer.class));

        if (uploadType == null) {
            throw new BizException("上传失败, 上传配置有误");
        }

        Map<String, AbstractUpload> uploadMap = SpringUtil.getBeansOfType(AbstractUpload.class);
        for (AbstractUpload abstractUpload : uploadMap.values()) {
            if (abstractUpload.checkUse(uploadType)) {
                // 如果在系统中找到了对应文件的MD5，本次将不在上传,直接获取历史数据
                Attach checkAttach = abstractUpload.checkAttach(md5, uploadType);
                if (checkAttach != null) {
                    return checkAttach;
                }
                return abstractUpload.upload(fileBytes, originalName, md5);
            }
        }

        throw new BizException("上传失败, 无法找到任何上传方式");
    }
}
