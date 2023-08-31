package com.yuxuan66.ecmc.cache.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2023/1/13
 */
@Data
@TableName("eve_type_alias")
public class TypeAlias extends Model<TypeAlias> {

    private Long id;
    private String name;

    @TableField("`alias`")
    private String alias;
}
