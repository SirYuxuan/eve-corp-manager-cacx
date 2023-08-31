package com.yuxuan66.ecmc.modules.lp.entity.query;

import com.yuxuan66.ecmc.modules.lp.entity.LpGoodsOrder;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/12/14
 */
@Data
public class LpGoodsOrderQuery extends BaseQuery<LpGoodsOrder> {

    private Integer status;
}
