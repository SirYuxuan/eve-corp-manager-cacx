package com.yuxuan66.ecmc.cache.mapper;

import com.yuxuan66.ecmc.cache.entity.Group;
import com.yuxuan66.ecmc.support.base.BaseMapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2021/12/15
 */
public interface GroupMapper extends BaseMapper<Group> {

    /**
     * 批量插入eve group
     * @param groupList group集合
     * @return 插入条数
     */
    long batchInsert(List<Group> groupList);
}
