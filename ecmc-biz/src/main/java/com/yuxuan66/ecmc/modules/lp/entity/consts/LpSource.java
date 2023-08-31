
package com.yuxuan66.ecmc.modules.lp.entity.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * @author Sir丶雨轩
 * @since 2022/9/19
 */
public enum LpSource {


    /**
     * PAP转换
     */
    PAP(1, "PAP转换"),
    /**
     * 手动发放
     */
    MANUAL_RELEASE(2, "手动发放"),
    /**
     * 用户转账
     */
    USER_TRANSFER(3, "用户转账"),
    /**
     * 兑换商品
     */
    EXCHANGE_GOODS(4, "兑换商品"),
    /**
     * 兑换退款
     */
    EXCHANGE_REFUND(5, "兑换退款"),
    /**
     * 物品兑换
     */
    ITEM_EXCHANGE(6, "物品兑换"),
    /**
     * 超网订单
     */
    ULTRA_NET_ORDER(7, "超网订单");

    @JsonValue
    @EnumValue
    private final Integer value;
    private String name;

    LpSource(Integer value, String name) {
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
