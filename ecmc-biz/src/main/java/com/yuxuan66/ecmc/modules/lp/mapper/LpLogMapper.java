package com.yuxuan66.ecmc.modules.lp.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuxuan66.ecmc.modules.lp.entity.LpLog;
import com.yuxuan66.ecmc.modules.lp.entity.query.LpLogQuery;
import com.yuxuan66.ecmc.support.base.BaseMapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
public interface LpLogMapper extends BaseMapper<LpLog> {

    /**
     * 批量插入lp日志
     * @param lpLogList lp记录
     * @return 插入条数
     */
    long batchInsert(List<LpLog> lpLogList);

    /**
     * 分页查询LP发放记录
     * @param page 分页信息
     * @param query 查询条件
     * @return LP发放记录
     */
    Page<LpLog> selectLpLog(Page<LpLog> page,LpLogQuery query);

}
