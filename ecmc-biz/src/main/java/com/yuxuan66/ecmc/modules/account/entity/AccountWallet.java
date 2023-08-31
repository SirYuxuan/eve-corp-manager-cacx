package com.yuxuan66.ecmc.modules.account.entity;

import java.sql.Timestamp;
import java.io.Serializable;

import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 军团成员钱包流水(CorpAccountWallet)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-12-24 12:59:43
 */
@Getter
@Setter
@TableName("corp_account_wallet")
public class AccountWallet extends BaseEntity<AccountWallet> implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    private Long journalId;
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
     * 交易金额
     */
    private Double amount;
    /**
     * 余额
     */
    private Double balance;
    /**
     * 上下文ID
     */
    private Long contextId;
    /**
     * 上下文类型
     */
    private String contextIdType;
    /**
     * 交易时间
     */
    private Timestamp date;
    /**
     * 第一方ID
     */
    private Integer firstPartyId;
    /**
     * 第一方名称
     */
    private String firstPartyName;
    /**
     * 第一方类型
     */
    private String firstPartyType;
    /**
     * 交易描述
     */
    private String description;
    /**
     * 交易原因
     */
    private String reason;
    /**
     * 交易类型
     */
    private String refType;
    /**
     * 第三方ID
     */
    private Integer secondPartyId;
    /**
     * 第三方名称
     */
    private String secondPartyName;
    /**
     * 第三方类型
     */
    private String secondPartyType;
    /**
     * 纳税数额
     */
    private Double tax;
    /**
     * 税务记录ID
     */
    private Integer taxReceiverId;



}

