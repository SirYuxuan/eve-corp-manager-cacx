package com.yuxuan66.ecmc.modules.lp.entity.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * @author Sir丶雨轩
 * @since 2022/9/19
 */
public enum LpType {


    /**
     * 支出
     */
    EXPENDITURE(1, "支出"),
    /**
     * 收入
     */
    INCOME(2, "收入");;

    @JsonValue
    @EnumValue
    private final Integer value;
    private String name;

    LpType(Integer value, String name) {
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
