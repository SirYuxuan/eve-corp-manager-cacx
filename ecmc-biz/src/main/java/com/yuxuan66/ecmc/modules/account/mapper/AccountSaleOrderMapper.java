package com.yuxuan66.ecmc.modules.account.mapper;

import com.yuxuan66.ecmc.modules.account.entity.AccountOrder;
import com.yuxuan66.ecmc.modules.account.entity.AccountSaleOrder;
import com.yuxuan66.ecmc.support.base.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/24
 */
public interface AccountSaleOrderMapper extends BaseMapper<AccountSaleOrder> {
    /**
     * 批量保存用户订单数据
     * @param list 订单数据
     * @return 插入条数
     */
    long batchInsert(List<AccountSaleOrder> list);

}
