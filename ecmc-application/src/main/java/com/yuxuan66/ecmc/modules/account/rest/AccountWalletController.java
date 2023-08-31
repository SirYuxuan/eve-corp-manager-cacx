package com.yuxuan66.ecmc.modules.account.rest;

import com.yuxuan66.ecmc.modules.account.entity.AccountWallet;
import com.yuxuan66.ecmc.modules.account.entity.query.AccountWalletQuery;
import com.yuxuan66.ecmc.modules.account.service.AccountWalletService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sir丶雨轩
 * @since 2022/12/25
 */
@RestController
@RequestMapping(path = "/accountWallet")
public class AccountWalletController extends BaseController<AccountWalletService> {


    /**
     * 查询自己的钱包交易流水
     * @param query 查询条件
     * @return 交易流水
     */
    @GetMapping
    public Ps list(AccountWalletQuery query) {
        return baseService.list(query);
    }

}
