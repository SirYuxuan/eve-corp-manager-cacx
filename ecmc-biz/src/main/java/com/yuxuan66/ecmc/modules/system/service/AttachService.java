package com.yuxuan66.ecmc.modules.system.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.digest.MD5;
import com.yuxuan66.ecmc.common.upload.UploadContext;
import com.yuxuan66.ecmc.common.upload.entity.Attach;
import com.yuxuan66.ecmc.common.upload.mapper.AttachMapper;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
@Service
@RequiredArgsConstructor
public class AttachService extends BaseService<Attach, AttachMapper> {

    private final UploadContext uploadContext;

    /**
     * 文件批量上传
     *
     * @param files 文件列表
     * @return 上传后文件
     */
    public List<Attach> upload(MultipartFile[] files) {
        List<Attach> attachList = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                byte[] fileBytes = file.getBytes();
                String md5 = MD5.create().digestHex(fileBytes);
                Attach attach = uploadContext.upload(fileBytes, file.getOriginalFilename(), md5);
                Assert.notNull(attach, () -> new BizException("文件上传失败."));
                attachList.add(attach);
            } catch (IOException e) {
                throw new BizException("文件上传失败", e);
            }
        }
        // 过滤掉已经在数据库中的
        List<Attach> saveAttachList = attachList.stream().filter(item -> item.getId() == null).toList();
        if (!saveAttachList.isEmpty()) {
            baseMapper.batchInsert(saveAttachList);
        }
        return attachList;
    }

}
