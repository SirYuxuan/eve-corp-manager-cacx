package com.yuxuan66.ecmc.modules.account.entity.query;

import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
@Data
public class UserAccountQuery extends BaseQuery<UserAccount> {

    private Long userId;

    private boolean all = false;
}
