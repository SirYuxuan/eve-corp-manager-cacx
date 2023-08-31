package com.yuxuan66.ecmc.cache.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * EVE蓝图(EveBluePrint)实体类
 *
 * @author makejava
 * @since 2021-12-16 10:02:37
 */
@Setter
@Getter
@TableName("eve_blue_print")
public class BluePrint extends Model<BluePrint> implements Serializable {
    /**
     * 蓝图ID
     */
    private Integer id;
    /**
     * 蓝图名称
     */
    private String name;
    /**
     * 蓝图名称英文
     */
    private String nameEn;
    /**
     * 最大流程数
     */
    private Integer maxLimit;
    /**
     * 复制时间
     */
    private Integer copyTime;
    /**
     * 制造时间
     */
    private Integer manufacturingTime;
    /**
     * 材料优化时间
     */
    private Integer researchMaterialTime;
    /**
     * 时间优化时间
     */
    private Integer researchTimeTime;
    /**
     * 发明时间
     */
    private Integer inventionTime;
    /**
     * 反应时间
     */
    private Integer reactionTime;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    private Integer groupId;
    private String groupName;
    private String groupNameEn;

    private Integer metaGroupId;
    private String metaGroupName;
    private String metaGroupNameEn;

    private Integer marketGroupId;
    private String marketGroupName;
    private String marketGroupNameEn;

    private Integer categoryId;
    private String categoryName;
    private String categoryNameEn;

    @TableField(exist = false)
    private List<BluePrintProducts> printProductsList;
    @TableField(exist = false)
    private List<BluePrintMaterials> printMaterialsList;
    @TableField(exist = false)
    private List<BluePrintSkill> printSkillList;


}

