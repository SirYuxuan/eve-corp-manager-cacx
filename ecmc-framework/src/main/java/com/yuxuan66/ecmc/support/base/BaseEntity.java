package com.yuxuan66.ecmc.support.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.yuxuan66.ecmc.common.utils.excel.annotation.ExcelColumn;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Data
public class BaseEntity<T extends Model<?>> extends Model<T>{

    @TableId(type = IdType.AUTO)
    private Long id;


    /**
     * 创建时间0
     */
    @ExcelColumn(name = "创建时间",sort = 10000)
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createId;
    /**
     * 创建人
     */
    @ExcelColumn(name = "创建人",sort = 10001)
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 更新时间
     */
    @ExcelColumn(name = "更新时间",sort = 10002)
    @TableField(fill = FieldFill.UPDATE)
    private Timestamp updateTime;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updateId;
    /**
     * 更新人
     */
    @ExcelColumn(name = "更新人",sort = 10003)
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;
}
