package com.yuxuan66.ecmc.modules.system.entity.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * 用户状态枚举
 *
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public enum UserStatus{

    /**
     * 正常
     */
    NORMAL(0, "正常"),
    /**
     * 冻结
     */
    FROZEN(1, "冻结"),
    /**
     * 锁定
     */
    LOCKING(2, "锁定");
    @JsonValue
    @EnumValue
    private final Integer value;
    private final String name;

    UserStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer value() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }


    public boolean equals(Integer status) {
        return Objects.equals(status, this.value);
    }


}
