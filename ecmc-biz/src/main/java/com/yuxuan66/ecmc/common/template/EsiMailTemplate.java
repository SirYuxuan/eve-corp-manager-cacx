package com.yuxuan66.ecmc.common.template;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.modules.account.entity.AccountKillMail;
import com.yuxuan66.ecmc.modules.lp.entity.LpGoodsOrder;
import com.yuxuan66.ecmc.modules.lp.entity.dto.OrderDto;
import com.yuxuan66.ecmc.modules.utils.entity.SrpLog;

/**
 * ESI 邮件发送模板
 *
 * @author Sir丶雨轩
 * @since 2022/12/14
 */
public class EsiMailTemplate {

    /**
     * 获取LP订单审批通知模板
     *
     * @param order    订单信息
     * @param orderDto 审批信息
     * @return 通知内容
     */
    public static String getOrderApprovalNotice(LpGoodsOrder order, OrderDto orderDto) {
        return StrUtil.format("""
                您好,{} 您于{}申请兑换的[{}]*{}, 已经审批完成 <br>
                状态: {}<br>
                备注: {}<br>
                <a href={}>详情点击进入军团网站查询</a>
                 """, order.getCreateBy(), DateUtil.formatDateTime(order.getCreateTime()), order.getTitle(), order.getNum(), orderDto.getStatus() ? "通过" : "拒绝", orderDto.getSpContent(), ConfigKit.get(CacheKey.WEB_SITE));
    }

    /**
     * 获取用户兑换LP商品的通知模板
     *
     * @return 通知内容
     */
    public static String getNewLpOrderNotice(LpGoodsOrder goodsOrder) {
        return StrUtil.format("""
                您好, 我兑换了LP商品[{}]*{}, 请前往<a href={}>军团网站</a>审批 <br>
                申请人: {} <br>
                申请备注: {} <br>
                申请时间: {}
                """, goodsOrder.getTitle(), goodsOrder.getNum(), ConfigKit.get(CacheKey.WEB_SITE), goodsOrder.getCreateBy(), Convert.toStr(goodsOrder.getContent(), StrUtil.EMPTY), goodsOrder.getCreateTime());
    }

    /**
     * 获取补损审批完成的通知模板
     * @param srpLog 补损记录
     * @param killMail 击毁记录
     * @return 通知模板
     */
    public static String getSrpNotice(SrpLog srpLog, AccountKillMail killMail) {
        return StrUtil.format("""
                您好, 您于[{}]提交的[{}]补损已经审批完成. <br>
                审批状态: {}<br>
                审批备注: {}<br>
                审批时间: {}<br>
                审批人: {}<br>
                """, srpLog.getCreateTime(), killMail.getShipTypeName(), srpLog.getStatus().getName(), Convert.toStr(srpLog.getSpContent(), ""), srpLog.getUpdateTime(), srpLog.getUpdateBy());
    }
}
