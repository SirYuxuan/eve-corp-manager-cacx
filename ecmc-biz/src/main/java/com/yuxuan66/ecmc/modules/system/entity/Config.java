package com.yuxuan66.ecmc.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统-配置表(SysConfig)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-09-08 17:34:50
 */
@Data
@TableName("sys_config")
public class Config extends BaseEntity<Config> implements Serializable {

    @Serial
    private static final long serialVersionUID = 905795302830286810L;

    /**
     * 配置标题
     */
    private String title;
    /**
     * 配置名称
     */
    private String name;
    /**
     * 配置值
     */
    private String value;
    /**
     * 备注
     */
    private String remark;


}

