package com.yuxuan66.ecmc.modules.system.entity.dto;

import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
@Data
public class ResetPasswordDto {

    private String account;
    private String sms;

    private String password;
}
