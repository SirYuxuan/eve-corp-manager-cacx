package com.yuxuan66.ecmc.modules.lp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * LP商品表(CorpLpGoods)实体类
 *
 * @author makejava
 * @since 2022-01-10 09:48:59
 */
@Setter
@Getter
@TableName("corp_lp_goods")
public class LpGoods  extends BaseEntity<LpGoods> implements Serializable {


    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品类型
     */
    private String type;
    /**
     * 所需要的LP
     */
    private BigDecimal lp;
    /**
     * 商品总数量
     */
    private Integer num;
    /**
     * 已销售数量
     */
    private Integer shopNum;
    /**
     * 商品图片
     */
    private String pics;




}

