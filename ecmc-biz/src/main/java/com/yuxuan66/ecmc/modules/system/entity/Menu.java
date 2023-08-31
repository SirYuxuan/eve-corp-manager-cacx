package com.yuxuan66.ecmc.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.ecmc.common.utils.tree.TreeField;
import com.yuxuan66.ecmc.common.utils.tree.TreeId;
import com.yuxuan66.ecmc.modules.system.entity.consts.MenuType;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统-菜单表(SysMenu)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-09-16 10:11:13
 */
@Data
@TableName("sys_menu")
public class Menu extends BaseEntity<Menu> implements Serializable {

    @Serial
    private static final long serialVersionUID = 413567790353332932L;

    @TreeId
    @TreeField
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父级ID
     */
    @TreeId(isParent = true)
    @TreeField
    private Long pid = 0L;
    /**
     * 菜单类型 0=目录,1=菜单,2=按钮
     */
    @TreeField(isEnum = true)
    private MenuType type;
    /**
     * 菜单图标
     */
    @TreeField(secondLevel = "meta")
    private String icon;

    @TreeField(secondLevel = "meta")
    private String name;

    @TreeField(secondLevel = "meta",like = "name")
    @TableField(exist = false)
    private String title;


    /**
     * 是否外链
     */
    @TreeField
    private Boolean isLink;

    /**
     * 是否内嵌
     */
    @TreeField
    private Boolean frame;
    /**
     * 是否缓存
     */
    @TreeField
    private Boolean cache;
    /**
     * 外链地址
     */
    @TreeField
    private String linkUrl;


    /**
     * 是否可见
     */
    @TreeField
    private Boolean hidden;
    /**
     * 权限字符串
     */
    @TreeField
    private String permission;
    /**
     * 路由地址
     */
    @TreeField
    private String path;

    /**
     * 组件地址
     */
    @TreeField
    private String component;

    /**
     * 排序号
     */
    @TreeField
    private Integer sort;


}

