package com.yuxuan66.ecmc.modules.system.entity.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * @author Sir丶雨轩
 * @since 2022/9/19
 */
public enum MenuType {

    /**
     * 目录
     */
    CATALOG(0, "目录"),
    /**
     * 菜单
     */
    MENU(1, "菜单"),
    /**
     * 按钮
     */
    BUTTON(2, "按钮");;

    @JsonValue
    @EnumValue
    private final Integer value;
    private String name;

    MenuType(Integer value, String name) {
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
