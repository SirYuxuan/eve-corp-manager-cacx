package com.yuxuan66.ecmc.cache.mapper;

import com.yuxuan66.ecmc.cache.entity.BluePrint;
import com.yuxuan66.ecmc.support.base.BaseMapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2021/12/16
 */
public interface BluePrintMapper extends BaseMapper<BluePrint> {

    /**
     * 批量插入
     * @param list 数据
     * @return 插入条数
     */
    long batchInsert(List<BluePrint> list);
}
