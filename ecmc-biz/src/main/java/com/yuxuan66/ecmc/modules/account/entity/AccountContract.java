package com.yuxuan66.ecmc.modules.account.entity;

import java.sql.Timestamp;
import java.io.Serializable;

import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 军团成员合同表(CorpAccountContract)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-12-27 09:42:40
 */
@Getter
@Setter
@TableName("corp_account_contract")
public class AccountContract extends BaseEntity<AccountContract> implements Serializable {


    private Integer contractId;
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
     * 接受人ID
     */
    private Integer acceptorId;
    /**
     * 接受人名称
     */
    private String acceptorName;
    private String acceptorType;
    /**
     * 受让人ID
     */
    private Integer assigneeId;
    /**
     * 受让人名称
     */
    private String assigneeName;
    private String assigneeType;
    /**
     * 有效范围
     */
    private String availability;
    /**
     * 一口价
     */
    private Double buyout;
    /**
     * 快递押金
     */
    private String collateral;
    /**
     * 接受时间
     */
    private Timestamp dateAccepted;
    /**
     * 完成时间
     */
    private Timestamp dateCompleted;
    /**
     * 过期时间
     */
    private Timestamp dateExpired;
    /**
     * 发起时间
     */
    private Timestamp dateIssued;
    /**
     * 完成天数
     */
    private Integer daysToComplete;
    /**
     * 快递目的地
     */
    private Long endLocationId;
    /**
     * 快递目的地名称
     */
    private String endLocationName;
    /**
     * 是否是军团合同
     */
    private Boolean forCorporation;
    /**
     * 发起军团ID
     */
    private Integer issuerCorporationId;
    /**
     * 发起军团名称
     */
    private String issuerCorporationName;
    /**
     * 合同发起人ID
     */
    private Integer issuerId;
    /**
     * 合同发起人名称
     */
    private String issuerName;
    /**
     * 合同价格
     */
    private Double price;
    /**
     * 快递奖金
     */
    private Double reward;
    /**
     * 快递开始地点ID
     */
    private Long startLocationId;
    /**
     * 快递开始地点名称
     */
    private String startLocationName;
    /**
     * 合同状态
     */
    private String status;
    /**
     * 合同标题
     */
    private String title;
    /**
     * 合同类型
     */
    private String type;
    
    private String volume;



}

