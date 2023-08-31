package com.yuxuan66.ecmc.modules.system.entity.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * 用户性别枚举
 *
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public enum UserSex {

    /**
     * 男
     */
    MALE(0, "男"),
    /**
     * 女
     */
    FEMALE(1, "女"),
    /**
     * 未知
     */
    UNKNOWN(2, "未知");
    @JsonValue
    @EnumValue
    private final Integer value;
    private String name;

    UserSex(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    public Integer value() {
        return this.value;
    }

    public boolean equals(Integer status) {
        return Objects.equals(status, this.value);
    }


}
