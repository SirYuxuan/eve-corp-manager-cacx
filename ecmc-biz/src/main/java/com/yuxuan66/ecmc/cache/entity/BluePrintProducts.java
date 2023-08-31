package com.yuxuan66.ecmc.cache.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * EVE蓝图产出表(包含制造、发明、反应的产出)(EveBluePrintProducts)实体类
 *
 * @author makejava
 * @since 2021-12-16 10:02:37
 */
@Setter
@Getter
@TableName("eve_blue_print_products")
public class BluePrintProducts extends Model<BluePrintProducts> implements Serializable {

    private Integer id;
    /**
     * 蓝图ID
     */
    private Integer bluePrintId;
    /**
     * 类型 1=发明产出，2=制造产出，3=反应产出
     */
    private Integer type;
    /**
     * 产出物品ID
     */
    private Integer productsId;
    /**
     * 产出物品名称
     */
    private String productsName;
    /**
     * 产出物品名称英文
     */
    private String productsNameEn;
    /**
     * 产出物品数量
     */
    private Integer productsQuantity;
    private Double probability;
    /**
     * 创建时间
     */
    private Timestamp createTime;


}

