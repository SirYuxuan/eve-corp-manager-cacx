package com.yuxuan66.ecmc.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

/**
 * 系统-角色表(SysRole)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-09-16 10:12:46
 */
@Data
@TableName("sys_role")
public class Role extends BaseEntity<Role> implements Serializable {

    @Serial
    private static final long serialVersionUID = -59483722353218104L;

    /**
     * 角色名
     */
    private String name;
    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单列表
     */
    @TableField(exist = false)
    private Set<Menu> menus;

}

