package com.yuxuan66.ecmc.common.upload.mapper;

import com.yuxuan66.ecmc.common.upload.entity.Attach;
import com.yuxuan66.ecmc.support.base.BaseMapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
public interface AttachMapper extends BaseMapper<Attach> {

    /**
     * 批量保存文件上传结果
     * @param list 文件列表
     * @return 插入条数
     */
    long batchInsert(List<Attach> list);
}
