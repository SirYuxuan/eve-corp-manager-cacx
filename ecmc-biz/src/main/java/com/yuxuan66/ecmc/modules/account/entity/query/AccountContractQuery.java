package com.yuxuan66.ecmc.modules.account.entity.query;

import com.yuxuan66.ecmc.modules.account.entity.AccountContract;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/12/27
 */
@Data
public class AccountContractQuery extends BaseQuery<AccountContract> {

    private Long accountId;
    private String issuerName;
    private String assigneeName;
    private String startLocationName;
    private String status;
    private String type;
}
