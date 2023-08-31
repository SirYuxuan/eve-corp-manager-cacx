package com.yuxuan66.ecmc.cache.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * EVE分类表(EveCategory)实体类
 *
 * @author makejava
 * @since 2021-12-15 15:43:59
 */
@Setter
@Getter
@TableName("eve_category")
public class Category extends Model<Category> implements Serializable {

    private Integer id;
    
    private String name;
    
    private String nameEn;
    
    private Timestamp createTime;


}

