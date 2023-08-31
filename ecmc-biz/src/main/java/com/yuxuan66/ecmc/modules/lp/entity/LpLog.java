package com.yuxuan66.ecmc.modules.lp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.lp.entity.consts.LpSource;
import com.yuxuan66.ecmc.modules.lp.entity.consts.LpType;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 军团LP发放记录表(CorpLpLog)实体类
 *
 * @author makejava
 * @since 2022-01-07 11:20:54
 */
@Data
@TableName("corp_lp_log")
public class LpLog extends BaseEntity<LpLog> implements Serializable {

    /**
     * 角色名
     */
    private String characterName;
    /**
     * 角色ID
     */
    private Long accountId;
    /**
     * 军团用户ID
     */
    private Long userId;
    /**
     * LP数量
     */
    private BigDecimal lp;
    /**
     * 1=PAP自动转换,2=手动发放,3=用户转账，4=兑换商品,5=兑换退款,6=物品兑换,7=超网节点
     */
    private LpSource source;
    /**
     * LP操作，1=支出，2=收入
     */
    private LpType type;
    /**
     * 说明
     */
    private String content;
    /**
     * 兑换商品的日志ID
     */
    private Long orderId;
    


    @TableField(exist = false)
    private UserAccount userAccount;



}

