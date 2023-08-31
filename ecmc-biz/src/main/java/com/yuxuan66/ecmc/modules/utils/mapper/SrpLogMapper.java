package com.yuxuan66.ecmc.modules.utils.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuxuan66.ecmc.modules.utils.entity.SrpLog;
import com.yuxuan66.ecmc.modules.utils.entity.query.SrpLogQuery;
import com.yuxuan66.ecmc.support.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
public interface SrpLogMapper extends BaseMapper<SrpLog> {

    /**
     * 分页查询补损提交记录
     * @param page 分页参数
     * @param logQuery 查询条件
     * @return 补损提交记录
     */
    Page<SrpLog> selectSrpLog(Page<SrpLog> page,@Param("query") SrpLogQuery logQuery);
}
