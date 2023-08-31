package com.yuxuan66.ecmc.common.upload;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.cache.StaticComp;
import com.yuxuan66.ecmc.common.upload.entity.Attach;
import com.yuxuan66.ecmc.common.upload.enums.UploadType;

import java.util.Date;
import java.util.List;

/**
 * 文件上传抽象类
 *
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
public abstract class AbstractUpload implements Upload {

    private static final String DATEFORMAT = "yyyy/MM/dd";

    protected String getDatePath() {
        return DateUtil.format(new Date(), DATEFORMAT);
    }

    /**
     * 统一格式化文件名
     *
     * @param originalFilename 原始文件名
     * @return 格式化后得文件名
     */
    @Override
    public String formatFileName(String originalFilename) {
        long timeName = System.currentTimeMillis();
        String ext = FileUtil.getSuffix(originalFilename);
        return timeName + "_" + RandomUtil.randomInt(10000, 99999) + "." + ext;
    }


    /**
     * 判断指定文件是否存在，如果存在返回对应的文件
     *
     * @param md5        文件md5
     * @param uploadType 上传类型
     * @return 文件
     */
    public Attach checkAttach(String md5, UploadType uploadType) {
        List<Attach> attachList = StaticComp.attachMapper.selectList(new QueryWrapper<Attach>().eq("md5", md5).eq("upload_type", uploadType.value()));
        if (!attachList.isEmpty()) {
            return attachList.get(0);
        }
        return null;
    }
}
