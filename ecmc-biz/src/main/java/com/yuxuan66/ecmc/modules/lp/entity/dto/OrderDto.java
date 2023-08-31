package com.yuxuan66.ecmc.modules.lp.entity.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author Sir丶雨轩
 * @since 2022/12/14
 */
@Data
public class OrderDto {

    private Boolean status;
    private String spContent;

    private Set<Long> ids;
}

