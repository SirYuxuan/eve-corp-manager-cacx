package com.yuxuan66.ecmc.modules.lp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.ecmc.modules.lp.entity.consts.OrderStatus;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 军团LP商品购买记录表(CorpGoodsBuyLog)实体类
 *
 * @author makejava
 * @since 2022-01-10 12:53:53
 */
@Data
@TableName("corp_lp_goods_order")
public class LpGoodsOrder extends BaseEntity<LpGoodsOrder> implements Serializable {

    /**
     * 标题
     */
    private String title;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 状态1=等待，2=通过，3=拒绝
     */
    private OrderStatus status;
    /**
     * 兑换备注
     */
    private String content;
    /**
     * 审批备注
     */
    private String examineContent;

    /**
     * 申请人角色名称
     */
    private String characterName;
    /**
     * 申请人ID
     */
    private Integer characterId;



}

