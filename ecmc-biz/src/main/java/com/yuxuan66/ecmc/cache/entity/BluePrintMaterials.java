package com.yuxuan66.ecmc.cache.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * EVE蓝图材料表(包含制造、发明、研发、复制的材料)(EveBluePrintMaterials)实体类
 *
 * @author makejava
 * @since 2021-12-16 10:02:37
 */
@Setter
@Getter
@TableName("eve_blue_print_materials")
public class BluePrintMaterials extends Model<BluePrintMaterials> implements Serializable {

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
     * 材料ID
     */
    private Integer typeId;
    /**
     * 材料名称
     */
    private String typeName;
    /**
     * 材料名称英文
     */
    private String typeNameEn;
    /**
     * 数量
     */
    private Integer quantity;
    
    private Timestamp createTime;


}

