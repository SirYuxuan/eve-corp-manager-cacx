package com.yuxuan66.ecmc.modules.account.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 军团成员市场交易订单(CorpAccountOrder)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-12-26 16:14:00
 */
@Data

@TableName("corp_account_order")
public class AccountOrder extends BaseEntity<AccountOrder> implements Serializable {


    private Long orderId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色账号ID
     */
    private Long accountId;
    /**
     * 角色ID
     */
    private Integer characterId;
    /**
     * 角色名
     */
    private String characterName;
    /**
     * 客户ID
     */
    private Integer clientId;
    /**
     * 客户名称
     */
    private String clientName;
    private String clientType;
    /**
     * 交易时间
     */
    @TableField("`date`")
    private Timestamp date;
    /**
     * 是否是购买订单
     */
    private Boolean isBuy;
    /**
     * 是否是个人订单
     */
    private Integer isPersonal;

    private Long journalRefId;

    private Long locationId;

    private String locationName;

    private Integer quantity;

    /**
     * 单价
     */
    private Double unitPrice;

    private Integer typeId;

    private String typeName;


}

