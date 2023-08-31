package com.yuxuan66.ecmc.modules.account.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 军团成员KM记录详细损失表(CorpAccountKillMailItem)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-12-23 09:17:37
 */
@Data
@TableName("corp_account_kill_mail_item")
public class AccountKillMailItem  extends BaseEntity<AccountKillMailItem> implements Serializable {
    private static final long serialVersionUID = -91217153754446449L;
    
    /**
     * KMID
     */
    private Long killMailId;
    /**
     * 物品类型ID
     */
    private Integer typeId;
    /**
     * 物品名称
     */
    private String name;
    /**
     * 数量
     */
    private Long num;
    /**
     * 类型，掉落/损毁
     */
    private String type;
    /**
     * 估价
     */
    private BigDecimal price;



}

