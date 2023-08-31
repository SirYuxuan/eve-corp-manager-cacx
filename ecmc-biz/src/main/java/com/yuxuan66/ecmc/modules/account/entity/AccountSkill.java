package com.yuxuan66.ecmc.modules.account.entity;

import java.sql.Timestamp;
import java.io.Serializable;

import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 军团用户技能表(CorpAccountSkill)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-12-24 10:11:36
 */
@Data
@TableName("corp_account_skill")
public class AccountSkill extends BaseEntity<AccountSkill> implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色账号ID
     */
    private Long accountId;
    /**
     * 角色名
     */
    private String characterName;
    /**
     * 角色ID
     */
    private Integer characterId;
    
    private Integer activeSkillLevel;
    /**
     * 技能ID
     */
    private Integer skillId;
    /**
     * 技能名称
     */
    private String skillName;
    
    private Long skillPointsInSkill;
    
    private Integer trainedSkillLevel;


}

