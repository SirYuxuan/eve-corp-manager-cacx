package com.yuxuan66.ecmc.cache.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * EVE蓝图技能表(包含制造、发明、研发、复制的技能)(EveBluePrintSkill)实体类
 *
 * @author makejava
 * @since 2021-12-16 10:02:36
 */
@Setter
@Getter
@TableName("eve_blue_print_skill")
public class BluePrintSkill extends Model<BluePrintSkill> implements Serializable {

    private Integer id;
    /**
     * 蓝图ID
     */
    private Integer bluePrintId;
    /**
     * 类型 1=复制，2=材料优化，3=时间优化，4=发明，5=制造，6=反应
     */
    private Integer type;
    /**
     * 技能ID
     */
    private Integer skillsId;
    /**
     * 技能名称
     */
    private String skillsName;
    /**
     * 技能名称英文
     */
    private String skillsNameEn;
    /**
     * 等级
     */
    private Integer level;
    
    private Timestamp createTime;



}

