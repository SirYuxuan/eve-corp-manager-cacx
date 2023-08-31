package com.yuxuan66.ecmc.modules.account.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.common.utils.StpUtil;
import com.yuxuan66.ecmc.modules.account.entity.AccountWallet;
import com.yuxuan66.ecmc.modules.account.entity.query.AccountWalletQuery;
import com.yuxuan66.ecmc.modules.account.mapper.AccountWalletMapper;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import org.springframework.stereotype.Service;

/**
 * @author Sir丶雨轩
 * @since 2022/12/25
 */
@Service
public class AccountWalletService extends BaseService<AccountWallet, AccountWalletMapper> {

    /**
     * 查询自己的钱包交易流水
     * @param query 查询条件
     * @return 交易流水
     */
    public Ps list(AccountWalletQuery query) {
        query.processingCreateTime("date");
        QueryWrapper<AccountWallet> wrapper = query.getQueryWrapper();
        wrapper.orderByDesc("date");
        wrapper.eq(query.getAccountId() == null, "user_id", StpUtil.getLoginId());
        wrapper.eq(query.getAccountId() != null, "account_id", query.getAccountId());
        wrapper.like(query.getFirstPartyName() != null, "first_party_name", query.getFirstPartyName());
        wrapper.like(query.getSecondPartyName() != null, "second_party_name", query.getSecondPartyName());
        wrapper.eq(query.getRefType() != null, "ref_type", query.getRefType());
        return Ps.ok(page(query.getPage(), wrapper));
    }
}
