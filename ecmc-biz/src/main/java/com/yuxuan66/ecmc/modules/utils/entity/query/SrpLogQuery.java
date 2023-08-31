package com.yuxuan66.ecmc.modules.utils.entity.query;

import com.yuxuan66.ecmc.modules.utils.entity.SrpLog;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
@Data
public class SrpLogQuery extends BaseQuery<SrpLog> {

    private Long userId;
    private Integer status;

    private boolean all = false;
}
