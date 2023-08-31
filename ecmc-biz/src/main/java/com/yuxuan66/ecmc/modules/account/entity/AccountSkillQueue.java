package com.yuxuan66.ecmc.modules.account.entity;

import java.sql.Timestamp;
import java.io.Serializable;

import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 军团用户技能队列表(CorpAccountSkillQueue)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-12-24 12:11:22
 */
@Data
@TableName("corp_account_skill_queue")
public class AccountSkillQueue extends BaseEntity<AccountSkillQueue> implements Serializable {

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
     * 技能ID
     */
    private Integer skillId;
    /**
     * 技能名称
     */
    private String skillName;
    /**
     * 完成时间
     */
    private Timestamp finishDate;
    /**
     * 完成等级
     */
    private Integer finishedLevel;
    /**
     * 完成后技能点
     */
    private Integer levelEndSp;
    /**
     * 开始前技能点
     */
    private Integer levelStartSp;
    /**
     * 队列点数
     */
    private Integer queuePosition;
    /**
     * 开始时间
     */
    private Timestamp startDate;
    /**
     * 培训开始技能点
     */
    private Integer trainingStartSp;


}

