package com.yuxuan66.ecmc.modules.lp.entity.query;

import com.yuxuan66.ecmc.modules.lp.entity.LpLog;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
@Data
public class LpLogQuery extends BaseQuery<LpLog> {

    private Long accountId;
    private Long userId;

    private Integer source;
    private Integer type;
}
