package com.yuxuan66.ecmc.common.consts;

/**
 * 全局的一些常量
 *
 * @author Sir丶雨轩
 * @since 2022/12/14
 */
public interface Const {

    /**
     * LP审批权限码
     */
    String LP_ORDER_APPROVAL = "order:approval";
    /**
     * LP发放权限码
     */
    String LP_SEND = "lp:send";

    /**
     * 默认角色ID
     */
    Long DEFAULT_ROLE = 3L;

    /**
     * 军团成员的角色ID
     */
    Long CORP_ROLE = 4L;
    /**
     * LP管理员的角色ID
     */
    Long LP_ADMIN_ROLE = 5L;
    /**
     * 商城管理员的角色ID
     */
    Long SHOP_ADMIN_ROLE = 10L;
    /**
     * 补损官的角色ID
     */
    Long SRP_ADMIN_ROLE = 6L;
    /**
     * 总监的权限
     */
    Long DIRECTOR_ROLE = 11L;
}
