package com.yuxuan66.ecmc.modules.account.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.entity.query.UserAccountQuery;
import com.yuxuan66.ecmc.support.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Sir丶雨轩
 * @since 2022/12/12
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {

    /**
     * 分页查询 角色列表
     * @param page 分页参数
     * @param accountQuery 查询条件
     * @return 角色列表
     */
    Page<UserAccount> listUserAccount(Page<UserAccount> page,@Param("query") UserAccountQuery accountQuery);
}
