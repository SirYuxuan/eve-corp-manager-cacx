package com.yuxuan66.ecmc.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_roles_menus")
public class RolesMenus extends Model<RolesMenus> implements Serializable {

    @Serial
    private static final long serialVersionUID = -6307949042129238647L;

    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 虚拟
     */
    private Boolean virtually;
}
