package com.yuxuan66.ecmc.modules.system.entity.dto;

import lombok.Data;

/**
 * 登录对象
 * @author Sir丶雨轩
 * @since 2022/12/6
 */
@Data
public class LoginDto{

    private String username;

    private String password;

    private String code;

    private String uuid = "";

    private String loginType;
}
