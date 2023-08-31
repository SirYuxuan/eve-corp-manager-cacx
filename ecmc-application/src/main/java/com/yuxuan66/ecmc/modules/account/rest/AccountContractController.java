package com.yuxuan66.ecmc.modules.account.rest;

import com.yuxuan66.ecmc.modules.account.entity.AccountContractItem;
import com.yuxuan66.ecmc.modules.account.entity.query.AccountContractQuery;
import com.yuxuan66.ecmc.modules.account.entity.query.AccountOrderQuery;
import com.yuxuan66.ecmc.modules.account.service.AccountContractService;
import com.yuxuan66.ecmc.modules.account.service.AccountOrderService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/25
 */
@RestController
@RequestMapping(path = "/accountContract")
public class AccountContractController extends BaseController<AccountContractService> {


    /**
     * 查询自己的合同数据
     * @param query 查询条件
     * @return 合同数据
     */
    @GetMapping
    public Ps list(AccountContractQuery query) {
        return baseService.list(query);
    }

    /**
     * 查询一个合同的具体内容
     * @param contractId 合同ID
     * @return 合同内容
     */
    @GetMapping(path = "/listContractItem/{contractId}")
    public Rs listContractItem(@PathVariable Long contractId) {
        return Rs.ok(baseService.listContractItem(contractId));
    }

}
