package com.yuxuan66.ecmc.cache.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * EVE市场分组表(EveMarketGroup)实体类
 *
 * @author makejava
 * @since 2021-12-15 16:24:06
 */
@Setter
@Getter
@TableName("eve_market_group")
public class MarketGroup extends Model<MarketGroup> implements Serializable {
    /**
     * 市场分组ID
     */
    private Integer id;
    /**
     * 市场分组父ID
     */
    private Integer pid;


    @TableField(exist = false)
    private Boolean isLeaf;
    private String iconFile;
    /**
     * 名称
     */
    private String name;
    /**
     * 名称英文
     */
    private String nameEn;
    /**
     * 图标ID
     */
    private Integer iconId;
    /**
     * 描述
     */
    private String description;
    /**
     * 描述英文
     */
    private String descriptionEn;
    /**
     * 是否是类型
     */
    private Boolean hasType;
    /**
     * 创建时间
     */
    private Timestamp createTime;



}

