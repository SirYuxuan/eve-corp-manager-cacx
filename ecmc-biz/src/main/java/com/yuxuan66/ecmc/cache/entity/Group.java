package com.yuxuan66.ecmc.cache.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * (EveGroup)实体类
 *
 * @author makejava
 * @since 2021-12-15 16:02:11
 */
@Setter
@Getter
@TableName("eve_group")
public class Group extends Model<Group> implements Serializable {
    /**
     * 分组ID
     */
    private Integer id;

    private Integer iconId;
    /**
     * 分组名称
     */
    private String name;
    /**
     * 分组名称英文
     */
    private String nameEn;
    /**
     * 分类ID
     */
    private Integer categoryId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类名称英文
     */
    private String categoryNameEn;
    /**
     * 创建实践
     */
    private Timestamp createTime;



}

