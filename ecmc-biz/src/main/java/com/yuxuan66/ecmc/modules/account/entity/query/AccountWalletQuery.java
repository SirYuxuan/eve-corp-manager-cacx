package com.yuxuan66.ecmc.modules.account.entity.query;

import com.yuxuan66.ecmc.modules.account.entity.AccountWallet;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/12/25
 */
@Data
public class AccountWalletQuery extends BaseQuery<AccountWallet> {

    private Long accountId;


    private String firstPartyName;
    private String secondPartyName;
    private String refType;

}
