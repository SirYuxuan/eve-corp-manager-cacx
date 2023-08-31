package com.yuxuan66.ecmc.cache.mapper;

import com.yuxuan66.ecmc.cache.entity.BluePrintMaterials;
import com.yuxuan66.ecmc.support.base.BaseMapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2021/12/16
 */
public interface BluePrintMaterialsMapper extends BaseMapper<BluePrintMaterials> {

    /**
     * 批量插入
     * @param list 数据
     * @return 插入条数
     */
    long batchInsert(List<BluePrintMaterials> list);
}
