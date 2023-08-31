package com.yuxuan66.ecmc.cache.mapper;

import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.support.base.BaseMapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2021/12/15
 */
public interface TypeMapper extends BaseMapper<Type> {

    /**
     * 批量插入eve type数据
     * @param typeList type集合
     * @return 插入条数
     */
    long batchInsert(List<Type> typeList);
}
