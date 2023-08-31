package com.yuxuan66.ecmc.modules.system.entity.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author Sir丶雨轩
 * @since 2022/12/10
 */
@Data
public class SaveMenuDto {

    private Long roleId;

    private Set<Long> menuIds;
    private Set<Long> virtuallyMenuIds;
}
