package com.yuxuan66.ecmc.modules.account.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.common.utils.StpUtil;
import com.yuxuan66.ecmc.modules.account.entity.AccountOrder;
import com.yuxuan66.ecmc.modules.account.entity.query.AccountOrderQuery;
import com.yuxuan66.ecmc.modules.account.mapper.AccountOrderMapper;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import org.springframework.stereotype.Service;

/**
 * @author Sir丶雨轩
 * @since 2022/12/25
 */
@Service
public class AccountOrderService extends BaseService<AccountOrder, AccountOrderMapper> {

    /**
     * 查询自己的订单数据
     *
     * @param query 查询条件
     * @return 订单数据
     */
    public Ps list(AccountOrderQuery query) {
        query.processingCreateTime("date");
        QueryWrapper<AccountOrder> wrapper = query.getQueryWrapper();
        wrapper.orderByDesc("date");
        wrapper.like(StrUtil.isNotBlank(query.getClientName()), "client_name", query.getClientName());
        wrapper.like(StrUtil.isNotBlank(query.getTypeName()), "type_name", query.getTypeName());
        wrapper.eq(query.getAccountId() == null, "user_id", StpUtil.getLoginId());
        wrapper.eq(query.getAccountId() != null, "account_id", query.getAccountId());
        return Ps.ok(page(query.getPage(), wrapper));
    }
}
