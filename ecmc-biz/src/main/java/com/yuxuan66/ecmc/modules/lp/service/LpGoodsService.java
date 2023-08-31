package com.yuxuan66.ecmc.modules.lp.service;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.common.consts.Const;
import com.yuxuan66.ecmc.common.esi.api.MailApi;
import com.yuxuan66.ecmc.common.template.EsiMailTemplate;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.UserAccountMapper;
import com.yuxuan66.ecmc.modules.account.service.UserAccountService;
import com.yuxuan66.ecmc.modules.lp.entity.LpGoods;
import com.yuxuan66.ecmc.modules.lp.entity.LpGoodsOrder;
import com.yuxuan66.ecmc.modules.lp.entity.LpLog;
import com.yuxuan66.ecmc.modules.lp.entity.consts.LpSource;
import com.yuxuan66.ecmc.modules.lp.entity.consts.LpType;
import com.yuxuan66.ecmc.modules.lp.entity.consts.OrderStatus;
import com.yuxuan66.ecmc.modules.lp.entity.dto.BuyLpGoodsDto;
import com.yuxuan66.ecmc.modules.lp.mapper.LpGoodsMapper;
import com.yuxuan66.ecmc.modules.lp.mapper.LpLogMapper;
import com.yuxuan66.ecmc.modules.system.entity.User;
import com.yuxuan66.ecmc.modules.system.service.UserService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.exception.BizException;
import lombok.RequiredArgsConstructor;
import net.troja.eve.esi.model.Recipient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
@Service
@Transactional
@RequiredArgsConstructor
public class LpGoodsService extends BaseService<LpGoods, LpGoodsMapper> {

    private final ReentrantLock lock = new ReentrantLock();
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private LpLogMapper lpLogMapper;

    private final UserService userService;
    private final UserAccountService userAccountService;

    /**
     * 分页查询LP商品列表
     *
     * @param query 查询条件
     * @return 商品列表
     */
    public Ps list(BaseQuery<LpGoods> query) {
        query.processingBlurry("title", "type");
        query.processingCreateTime();
        QueryWrapper<LpGoods> wrapper = query.getQueryWrapper();
        return Ps.ok(page(query.getPage(), wrapper));
    }

    /**
     * 添加或修改一个LP商品
     *
     * @param lpGoods LP商品
     */
    public void addOrEdit(LpGoods lpGoods) {
        lpGoods.insertOrUpdate();
    }

    /**
     * 批量删除一组LP商品
     *
     * @param ids LP商品ID列表
     */
    public void del(Set<Long> ids) {
        removeBatchByIds(ids);
    }

    /**
     * 购买一个LP商品
     *
     * @param lpGoodsDto 购买信息
     */
    public void buyGoods(BuyLpGoodsDto lpGoodsDto) {
        lock.lock();
        try {
            UserAccount userAccount = userAccountMapper.selectById(lpGoodsDto.getAccountId());
            LpGoods lpGoods = getById(lpGoodsDto.getGoodsId());
            if (lpGoodsDto.getNum() > lpGoods.getNum() - lpGoods.getShopNum()) {
                throw new BizException("商品库存不足");
            }

            // 购买此商品所需要的LP
            BigDecimal needLp = lpGoods.getLp().multiply(Convert.toBigDecimal(lpGoodsDto.getNum()));
            List<UserAccount> accountList = userAccountService.all();
            BigDecimal nowLp = accountList.stream().map(UserAccount::getLpNow).reduce(BigDecimal.ZERO, BigDecimal::add);
            if (needLp.compareTo(nowLp) > 0) {
                throw new BizException("您的LP余额不足");
            }
            lpGoods.setShopNum(lpGoods.getShopNum() + lpGoodsDto.getNum());
            lpGoods.updateById();

            // 插入商品兑换记录
            LpGoodsOrder goodsOrder = new LpGoodsOrder();
            goodsOrder.setContent(lpGoodsDto.getContent());
            goodsOrder.setNum(lpGoodsDto.getNum());
            goodsOrder.setTitle(lpGoods.getTitle());
            goodsOrder.setStatus(OrderStatus.WAIT);
            goodsOrder.setCharacterId(userAccount.getCharacterId());
            goodsOrder.setCharacterName(userAccount.getCharacterName());
            goodsOrder.insert();
            // 修改参与购买的角色的LP
            List<LpLog> logList = new ArrayList<>();
            for (UserAccount account : accountList) {
                // 如果所需的LP等于0，说明兑换商品需要的LP均已扣除
                if (needLp.compareTo(BigDecimal.ZERO) <= 0) {
                    break;
                }
                if (account.getLpNow().compareTo(BigDecimal.ZERO) <= 0) {
                    continue;
                }

                // 判断当前所需的LP 当前角色是否充足
                BigDecimal useLp = needLp;

                // 如果需要的LP大于当前角色余额
                // 1. 本次使用的LP = 角色LP数量
                // 2. needLP = needLP - userLp
                if(needLp.doubleValue() > account.getLpNow().doubleValue()){
                    useLp = account.getLpNow();
                }else{
                    useLp = new BigDecimal(Convert.toStr(needLp));
                }

                needLp = needLp.subtract(useLp);

                account.setLpNow(account.getLpNow().subtract(useLp));
                account.setLpUse(account.getLpUse().add(useLp));
                account.updateById();

                // 添加LP消费记录
                LpLog lpLog = new LpLog();
                lpLog.setContent("兑换[" + lpGoods.getTitle() + "] *" + lpGoodsDto.getNum());
                lpLog.setType(LpType.EXPENDITURE);
                lpLog.setSource(LpSource.EXCHANGE_GOODS);
                lpLog.setCharacterName(account.getCharacterName());
                lpLog.setOrderId(goodsOrder.getId());
                lpLog.setLp(useLp);
                lpLog.setAccountId(account.getId());
                lpLog.setUserId(account.getUserId());
                logList.add(lpLog);
            }
            if (!logList.isEmpty()) {
                lpLogMapper.batchInsert(logList);
            }
            // 发送邮件通知审核人员
            // 1. 查找出有权限审批的人员
            List<Long> userIdList = userService.findByPermCode(Const.LP_ORDER_APPROVAL).stream().map(User::getId).toList();
            List<UserAccount> approvalUserList = userAccountMapper.selectList(new QueryWrapper<UserAccount>().in("user_id", userIdList).eq("is_main", true));
            if (!approvalUserList.isEmpty()) {
                // 发送邮件
                List<MailApi.Recipient> recipientList = new ArrayList<>();
                approvalUserList.forEach(item -> recipientList.add(new MailApi.Recipient(item.getCharacterId(), Recipient.RecipientTypeEnum.CHARACTER)));
                MailApi.send(userAccount, "LP商品兑换通知,请前往军团网站审批.", EsiMailTemplate.getNewLpOrderNotice(goodsOrder), recipientList);
            }

        } finally {
            lock.unlock();
        }

    }

}
