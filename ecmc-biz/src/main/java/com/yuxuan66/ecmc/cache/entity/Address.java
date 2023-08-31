package com.yuxuan66.ecmc.cache.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * EVE地名对照(EveAddress)实体类
 *
 * @author makejava
 * @since 2022-01-13 16:34:43
 */
@Data
@TableName("eve_address")
public class Address implements Serializable {

    private Integer id;
    
    private String name;
    
    private String nameEn;


}

