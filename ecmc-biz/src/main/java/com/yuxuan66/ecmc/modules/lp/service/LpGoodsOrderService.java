package com.yuxuan66.ecmc.modules.lp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.yuxuan66.ecmc.common.consts.Const;
import com.yuxuan66.ecmc.common.esi.api.MailApi;
import com.yuxuan66.ecmc.common.template.EsiMailTemplate;
import com.yuxuan66.ecmc.common.utils.StpUtil;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.UserAccountMapper;
import com.yuxuan66.ecmc.modules.account.service.UserAccountService;
import com.yuxuan66.ecmc.modules.lp.entity.LpGoodsOrder;
import com.yuxuan66.ecmc.modules.lp.entity.LpLog;
import com.yuxuan66.ecmc.modules.lp.entity.consts.LpSource;
import com.yuxuan66.ecmc.modules.lp.entity.consts.LpType;
import com.yuxuan66.ecmc.modules.lp.entity.consts.OrderStatus;
import com.yuxuan66.ecmc.modules.lp.entity.dto.OrderDto;
import com.yuxuan66.ecmc.modules.lp.entity.query.LpGoodsOrderQuery;
import com.yuxuan66.ecmc.modules.lp.mapper.LpGoodsOrderMapper;
import com.yuxuan66.ecmc.modules.lp.mapper.LpLogMapper;
import com.yuxuan66.ecmc.modules.system.service.UserService;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.troja.eve.esi.model.Recipient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class LpGoodsOrderService extends BaseService<LpGoodsOrder, LpGoodsOrderMapper> {

    private final UserService userService;
    private final UserAccountService userAccountService;
    @Resource
    private LpLogMapper lpLogMapper;
    @Resource
    private UserAccountMapper userAccountMapper;

    /**
     * 分页查询LP商品兑换订单
     *
     * @param baseQuery 查询条件
     * @return 商品订单
     */
    public Ps list(LpGoodsOrderQuery baseQuery) {
        Long loginId = StpUtil.getLoginId();
        baseQuery.processingCreateTime();
        baseQuery.processingBlurry("title", "createBy", "content", "updateBy");
        QueryWrapper<LpGoodsOrder> wrapper = baseQuery.getQueryWrapper();
        wrapper.orderByDesc("create_time");
        wrapper.eq(baseQuery.getStatus() != null, "status", baseQuery.getStatus());
        if (!userService.getPermCode(loginId).contains(Const.LP_ORDER_APPROVAL)) {
            wrapper.eq("create_id", loginId);
        }
        return Ps.ok(page(baseQuery.getPage(), wrapper));
    }


    /**
     * 批量订单审批
     *
     * @param orderDto 订单信息
     */
    @SneakyThrows
    public void batchApproval(OrderDto orderDto) {

        for (Long orderId : orderDto.getIds()) {
            LpGoodsOrder order = getById(orderId);
            // 如果当前订单审批过了，直接跳过
            if (order.getStatus() != OrderStatus.WAIT) {
                continue;
            }
            // 更新订单表记录
            order.setStatus(orderDto.getStatus() ? OrderStatus.ADOPT : OrderStatus.REFUSE);
            order.setExamineContent(orderDto.getSpContent());
            order.updateById();
            // 如果是拒绝,需要退还购买使用的LP
            if (order.getStatus() == OrderStatus.REFUSE) {

                LambdaQueryWrapper<LpLog> wrapper = Wrappers.<LpLog>lambdaQuery().eq(LpLog::getOrderId, order.getId())
                        .eq(LpLog::getSource, LpSource.EXCHANGE_GOODS.value())
                        .eq(LpLog::getType, LpType.EXPENDITURE.value());
                List<LpLog> logList = lpLogMapper.selectList(wrapper);
                List<LpLog> returnLogList = Lists.newArrayListWithCapacity(logList.size());
                for (LpLog lpLog : logList) {
                    LpLog newLpLog = new LpLog();
                    newLpLog.setContent(lpLog.getContent() + " 审批拒绝自动退还");
                    newLpLog.setType(LpType.INCOME);
                    newLpLog.setSource(LpSource.EXCHANGE_REFUND);
                    newLpLog.setCharacterName(lpLog.getCharacterName());
                    newLpLog.setLp(lpLog.getLp());
                    newLpLog.setAccountId(lpLog.getAccountId());
                    newLpLog.setUserId(lpLog.getUserId());
                    returnLogList.add(newLpLog);

                    // 给用户加回LP
                    UserAccount userAccount = userAccountMapper.selectById(lpLog.getAccountId());
                    userAccount.setLpUse(userAccount.getLpUse().subtract(lpLog.getLp()));
                    userAccount.setLpNow(userAccount.getLpNow().add(lpLog.getLp()));
                    userAccount.updateById();
                }
                if (!returnLogList.isEmpty()) {
                    lpLogMapper.batchInsert(returnLogList);
                }

            }
            // 发送邮件
            MailApi.send(userAccountService.getMainAccount(),"LP商品兑换审批完成",EsiMailTemplate.getOrderApprovalNotice(order,orderDto), List.of(new MailApi.Recipient(order.getCharacterId(), Recipient.RecipientTypeEnum.CHARACTER)));
        }

    }
}
