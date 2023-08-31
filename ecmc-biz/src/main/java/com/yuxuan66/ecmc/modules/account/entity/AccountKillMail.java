package com.yuxuan66.ecmc.modules.account.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 军团成员KM记录表(CorpAccountKillMail)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-12-22 16:22:29
 */
@Getter
@Setter
@TableName("corp_account_kill_mail")
public class AccountKillMail extends BaseEntity<AccountKillMail> implements Serializable {
    @Serial
    private static final long serialVersionUID = 298630026936497409L;

    /**
     * 击杀ID
     */
    private Integer killMailId;
    /**
     * 击杀Hash
     */
    private String killMailHash;
    /**
     * 击杀时间
     */
    private Timestamp killMailTime;
    private Long accountId;
    private Long userId;
    /**
     * 是否是NPC击毁
     */
    private Boolean isNpc;
    /**
     * 所在星系ID
     */
    private Integer solarSystemId;
    /**
     * 所在星系名称
     */
    private String solarSystemName;
    /**
     * 所受损失
     */
    private BigDecimal damageTaken;
    /**
     * 舰船ID
     */
    private Integer shipTypeId;
    /**
     * 舰船名称
     */
    private String shipTypeName;
    /**
     * 角色ID
     */
    private Integer characterId;
    /**
     * 角色名称
     */
    private String characterName;
    /**
     * 军团ID
     */
    private Integer corporationId;
    /**
     * 军团名称
     */
    private String corporationName;
    /**
     * 联盟ID
     */
    private Integer allianceId;
    /**
     * 联盟名称
     */
    private String allianceName;
    /**
     * 是否已经提交SRP
     */
    @TableField(exist = false)
    private boolean srp;


}

