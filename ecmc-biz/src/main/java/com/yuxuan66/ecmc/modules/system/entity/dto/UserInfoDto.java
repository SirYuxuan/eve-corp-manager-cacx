package com.yuxuan66.ecmc.modules.system.entity.dto;

import com.yuxuan66.ecmc.modules.system.entity.Role;
import lombok.Data;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/9
 */
@Data
public class UserInfoDto {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String realName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 描述
     */
    private String desc;

    /**
     * 角色列表
     */
    private List<Role> roles;
}
