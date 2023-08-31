package com.yuxuan66.ecmc.modules.utils.entity.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
@AllArgsConstructor
@Getter
public enum SrpLogStatus {

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

    ;

    @JsonValue
    @EnumValue
    private final Integer value;
    private String name;


}
