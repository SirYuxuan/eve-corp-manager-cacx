package com.yuxuan66.ecmc.modules.system.entity.dto;

import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
@Data
public class RegisterDto {

    private String email;
    private String code;

    private String username;

    private String password;
}
