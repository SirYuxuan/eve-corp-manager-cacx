package com.yuxuan66.ecmc.cache.mapper;

import com.yuxuan66.ecmc.cache.entity.MarketGroup;
import com.yuxuan66.ecmc.support.base.BaseMapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2021/12/15
 */
public interface MarketGroupMapper extends BaseMapper<MarketGroup> {

    /**
     * 批量插入eve market group
     * @param groupList  market group集合
     * @return 插入条数
     */
    long batchInsert(List<MarketGroup> groupList);
}
