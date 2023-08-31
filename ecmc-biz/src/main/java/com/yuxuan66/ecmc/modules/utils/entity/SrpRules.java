package com.yuxuan66.ecmc.modules.utils.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 军团补损规则表(CorpSrpRules)实体类
 *
 * @author makejava
 * @since 2022-01-11 09:44:42
 */
@Data
@TableName("corp_srp_rules")
public class SrpRules extends BaseEntity<SrpRules> implements Serializable {

    private Long id;
    /**
     * 舰船名称
     */
    private String shipName;
    private Integer shipId;
    /**
     * 是否支持NPC击杀
     */
    private Boolean isNpc;
    /**
     * 入团多少天内支持补损
     */
    private Integer joinTime;
    /**
     * 是否禁止补损
     */
    private Boolean notSrp;
    




}

