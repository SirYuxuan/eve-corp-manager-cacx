package com.yuxuan66.ecmc.modules.utils.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 补损规则黑名单(CorpSrpBlacklist)实体类
 *
 * @author makejava
 * @since 2022-01-11 09:32:52
 */
@Data
@TableName("corp_srp_blacklist")
public class SrpBlacklist extends BaseEntity<SrpBlacklist> implements Serializable {

    private Long id;
    
    private Long userId;
    /**
     * 角色名字
     */
    private String name;
    private Integer characterId;
    /**
     * 是否连坐其他角色
     */
    private Boolean isFull;
    /**
     * 截至时间
     */
    private Timestamp endTime;
    /**
     * 开始时间
     */
    private Timestamp startTime;
    /**
     * 拦截原因
     */
    private String remark;


}

