package com.yuxuan66.ecmc.modules.system.mapper;

import com.yuxuan66.ecmc.modules.system.entity.Role;
import com.yuxuan66.ecmc.support.base.BaseMapper;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 分页查询角色列表
     *
     * @param roleQuery 查询参数
     * @return 用户列表
     */
    List<Role> listRole(@Param("query") BaseQuery<Role> roleQuery);

    /**
     * 计算角色总条数
     * @param roleQuery 查询参数
     * @return 条数
     */
    long countRole(@Param("query") BaseQuery<Role> roleQuery);


    /**
     * 根据用户ID查询角色列表
     * @param userId 用户id
     * @return 角色列表
     */
    @Select("select * from sys_role where id in (select role_id from sys_users_roles where user_id = #{userId} )")
    List<Role> selectRoleByUserId(Long userId);

}
