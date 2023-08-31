package com.yuxuan66.ecmc.modules.account.entity;

import java.sql.Timestamp;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 军团成员市场在售订单(CorpAccountSaleOrder)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-12-26 18:29:32
 */
@Data
@TableName("corp_account_sale_order")
public class AccountSaleOrder extends BaseEntity<AccountSaleOrder> implements Serializable {


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
     * 时效
     */
    private Integer duration;
    
    private Integer escrow;
    /**
     * 是否是购买订单
     */
    private Boolean isBuyOrder;
    /**
     * 是否是军团订单
     */
    private Boolean isCorporation;
    /**
     * 发布时间
     */
    private Timestamp issued;
    /**
     * 位置ID
     */
    private Long locationId;
    /**
     * 位置名称
     */
    private String locationName;
    /**
     * 价格
     */
    private Double price;
    /**
     * 范围
     */
    @TableField("`range`")
    private String range;
    /**
     * 区域ID
     */
    private Integer regionId;
    /**
     * 区域名称
     */
    private String regionName;
    /**
     * 物品ID
     */
    private Integer typeId;
    /**
     * 物品名称
     */
    private String typeName;
    /**
     * 剩余数量
     */
    private Integer volumeRemain;
    /**
     * 上架数量
     */
    private Integer volumeTotal;



}

