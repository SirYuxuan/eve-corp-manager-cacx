package com.yuxuan66.ecmc.modules.account.mapper;

import com.yuxuan66.ecmc.modules.account.entity.AccountOrder;
import com.yuxuan66.ecmc.support.base.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/24
 */
public interface AccountOrderMapper extends BaseMapper<AccountOrder> {
    /**
     * 批量保存用户订单数据
     * @param list 订单数据
     * @return 插入条数
     */
    long batchInsert(List<AccountOrder> list);

    /**
     * 获取最后一条记录的ID
     * @param accountId 用户
     * @return ID
     */
    @Select("select * from corp_account_order where account_id = #{accountId} order by order_id desc limit 1")
    AccountOrder getLastId(Long accountId);
}
