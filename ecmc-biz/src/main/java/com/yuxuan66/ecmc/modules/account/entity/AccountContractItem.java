package com.yuxuan66.ecmc.modules.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 军团成员合同物品表(CorpAccountContractItem)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-12-27 09:42:40
 */
@Getter
@Setter
@TableName("corp_account_contract_item")
public class AccountContractItem extends Model<AccountContractItem> implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer contractId;
    /**
     * 如果合同签发人已将此项目与合同一起提交，则为true；如果isser要求在合同中提供此项目，则为false
     */
    private Boolean isIncluded;
    /**
     * 是否独立？
     */
    private Boolean isSingleton;
    /**
     * 物品数量
     */
    private Integer quantity;
    /**
     * -1表示该项是单例（不可堆叠）。如果项目恰好是蓝图，-1是原件，-2是蓝图副本
     */
    private Integer rawQuantity;
    /**
     * 针对这个合同物品的记录ID
     */
    private Long recordId;
    private Long accountId;
    /**
     * 物品名称
     */
    private Integer typeId;
    /**
     * 物品价格
     */
    private String typeName;
    /**
     * 物品SELL价
     */
    private Double sellPrice;
    /**
     * 物品BUY价
     */
    private Double buyPrice;


}

