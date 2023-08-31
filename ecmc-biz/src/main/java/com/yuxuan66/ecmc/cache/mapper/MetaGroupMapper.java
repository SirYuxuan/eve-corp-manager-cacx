package com.yuxuan66.ecmc.cache.mapper;

import com.yuxuan66.ecmc.cache.entity.MetaGroup;
import com.yuxuan66.ecmc.support.base.BaseMapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2021/12/15
 */
public interface MetaGroupMapper extends BaseMapper<MetaGroup> {

    /**
     * 批量插入eve meta group
     * @param groupList meta group集合
     * @return 插入条数
     */
    long batchInsert(List<MetaGroup> groupList);
}
