
package com.yuxuan66.ecmc.modules.lp.entity.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * @author Sir丶雨轩
 * @since 2022/9/19
 */
public enum OrderStatus {


    /**
     * 等待
     */
    WAIT(1, "等待"),
    /**
     * 通过
     */
    ADOPT(2, "通过"),
    /**
     * 拒绝
     */
    REFUSE(3, "拒绝");

    @JsonValue
    @EnumValue
    private final Integer value;
    private String name;

    OrderStatus(Integer value, String name) {
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
