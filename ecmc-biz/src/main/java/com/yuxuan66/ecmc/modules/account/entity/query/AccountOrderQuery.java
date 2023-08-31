package com.yuxuan66.ecmc.modules.account.entity.query;

import com.yuxuan66.ecmc.modules.account.entity.AccountOrder;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/12/25
 */
@Data
public class AccountOrderQuery extends BaseQuery<AccountOrder> {

    private Long accountId;

    private String clientName;

    private String typeName;



}
