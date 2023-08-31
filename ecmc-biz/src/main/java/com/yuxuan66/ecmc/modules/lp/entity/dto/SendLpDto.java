package com.yuxuan66.ecmc.modules.lp.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Sir丶雨轩
 * @since 2022/1/7
 */
@Data
public class SendLpDto {

    /**
     * 人员名单
     */
    private String lpUsers;

    /**
     * 发放数量
     */
    private BigDecimal lp;
    /**
     * 发放原因
     */
    private String where;
    /**
     * 是否只发给军团成员
     */
    private boolean corp;

}
