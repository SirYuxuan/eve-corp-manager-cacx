package com.yuxuan66.ecmc.modules.system.mapper;

import com.yuxuan66.ecmc.modules.system.entity.UsersRoles;
import com.yuxuan66.ecmc.support.base.BaseMapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
public interface UsersRolesMapper extends BaseMapper<UsersRoles> {

    /**
     * 批量添加用户角色关联
     * @param list 用户角色关联
     * @return 受影响行数
     */
    long batchInsert(List<UsersRoles> list);
}
