package com.yuxuan66.ecmc.cache.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * EVE所有的物品ID和名称(EveType)实体类
 *
 * @author makejava
 * @since 2021-12-15 14:49:32
 */
@Setter
@Getter
@TableName("eve_type")
public class Type extends Model<Type> implements Serializable {
    public final static Long serialVersionUID = 1L;
    private Integer id;
    /**
     * 物品名称
     */
    private String name;
    /**
     * 物品名称英文
     */
    private String nameEn;
    /**
     * 物品描述
     */
    private String description;
    /**
     * 物品描述英文
     */
    private String descriptionEn;
    /**
     * 分组ID
     */
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
    private String pinyin;


}

