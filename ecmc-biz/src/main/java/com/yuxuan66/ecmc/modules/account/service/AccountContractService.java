package com.yuxuan66.ecmc.modules.account.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.common.utils.StpUtil;
import com.yuxuan66.ecmc.modules.account.entity.AccountContract;
import com.yuxuan66.ecmc.modules.account.entity.AccountContractItem;
import com.yuxuan66.ecmc.modules.account.entity.query.AccountContractQuery;
import com.yuxuan66.ecmc.modules.account.mapper.AccountContractItemMapper;
import com.yuxuan66.ecmc.modules.account.mapper.AccountContractMapper;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/25
 */
@Service
public class AccountContractService extends BaseService<AccountContract, AccountContractMapper> {
    @Resource
    private AccountContractItemMapper accountContractItemMapper;

    /**
     * 查询自己的合同数据
     *
     * @param query 查询条件
     * @return 订单数据
     */
    public Ps list(AccountContractQuery query) {
        query.processingCreateTime("date_issued");
        QueryWrapper<AccountContract> wrapper = query.getQueryWrapper();
        wrapper.orderByDesc("date_issued");
        wrapper.eq(query.getAccountId()==null,"user_id", StpUtil.getLoginId());
        wrapper.like(query.getIssuerName() != null,"issuer_name",query.getIssuerName());
        wrapper.like(query.getAssigneeName() != null,"assignee_name",query.getAssigneeName());
        wrapper.like(query.getStartLocationName() != null,"start_location_name",query.getStartLocationName());
        wrapper.eq(query.getStatus() != null,"status",query.getStatus());
        wrapper.eq(query.getType() != null,"type",query.getType());
        return Ps.ok(page(query.getPage(), wrapper));
    }

    /**
     * 查询一个合同的具体内容
     * @param contractId 合同ID
     * @return 合同内容
     */
    public List<AccountContractItem> listContractItem(Long contractId) {
        if(contractId == -1L){
            return new ArrayList<>();
        }
        AccountContract contract = getById(contractId);
        return accountContractItemMapper.selectList(new QueryWrapper<AccountContractItem>().eq("contract_id", contract.getContractId()).eq("account_id",contract.getAccountId()));
    }
}
