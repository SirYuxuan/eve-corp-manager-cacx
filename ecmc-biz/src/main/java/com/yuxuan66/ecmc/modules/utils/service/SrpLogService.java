package com.yuxuan66.ecmc.modules.utils.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.common.esi.api.MailApi;
import com.yuxuan66.ecmc.common.template.EsiMailTemplate;
import com.yuxuan66.ecmc.common.utils.StpUtil;
import com.yuxuan66.ecmc.modules.account.entity.AccountKillMail;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.AccountKillMailMapper;
import com.yuxuan66.ecmc.modules.account.mapper.UserAccountMapper;
import com.yuxuan66.ecmc.modules.account.service.UserAccountService;
import com.yuxuan66.ecmc.modules.utils.entity.SrpBlacklist;
import com.yuxuan66.ecmc.modules.utils.entity.SrpLog;
import com.yuxuan66.ecmc.modules.utils.entity.SrpRules;
import com.yuxuan66.ecmc.modules.utils.entity.query.SrpLogQuery;
import com.yuxuan66.ecmc.modules.utils.mapper.SrpBlacklistMapper;
import com.yuxuan66.ecmc.modules.utils.mapper.SrpLogMapper;
import com.yuxuan66.ecmc.modules.utils.mapper.SrpRulesMapper;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.exception.BizException;
import lombok.RequiredArgsConstructor;
import net.troja.eve.esi.model.Recipient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class SrpLogService extends BaseService<SrpLog, SrpLogMapper> {

    @Resource
    private SrpBlacklistMapper srpBlacklistMapper;
    @Resource
    private SrpRulesMapper srpRulesMapper;
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private AccountKillMailMapper accountKillMailMapper;
    private final UserAccountService userAccountService;

    /**
     * 分页查询补损提交记录
     *
     * @param logQuery 查询条件
     * @return 补损提交记录
     */
    public Ps list(SrpLogQuery logQuery) {
        if (!logQuery.isAll()) {
            logQuery.setUserId(StpUtil.getLoginId());
        }

        return Ps.ok(baseMapper.selectSrpLog(logQuery.getPageOrder(), logQuery));
    }

    /**
     * 提交一条新的补损申请
     *
     * @param srpLog 补损记录
     */
    public void add(SrpLog srpLog) {
        if (count(Wrappers.<SrpLog>lambdaQuery().eq(SrpLog::getKillMailId, srpLog.getKillMailId())) > 0) {
            throw new BizException("此KM已经提交过了，请不要重复提交");
        }
        srpLog.setUserId(StpUtil.getLoginId());

        AccountKillMail killMail = accountKillMailMapper.selectById(srpLog.getKillMailId());

        // 黑名单拦截
        List<SrpBlacklist> srpBlacklistList = srpBlacklistMapper.selectList(new QueryWrapper<SrpBlacklist>().eq("user_id", StpUtil.getLoginId()).lt("start_time", killMail.getKillMailTime())
                .gt("end_time", killMail.getKillMailTime()));
        for (SrpBlacklist srpBlacklist : srpBlacklistList) {
            if (srpBlacklist.getIsFull() || srpBlacklist.getName().equals(killMail.getCharacterName())) {
                throw new BizException("由于【" + srpBlacklist.getRemark() + "】,您在【" + DateUtil.formatDateTime(srpBlacklist.getStartTime()) + "~" + DateUtil.formatDateTime(srpBlacklist.getEndTime()) + "】时间范围内的KM无法提交");
            }
        }

        // 规则拦截
        List<SrpRules> srpRulesList = srpRulesMapper.selectList(new QueryWrapper<SrpRules>().eq("ship_id", killMail.getShipTypeId()));
        for (SrpRules srpRules : srpRulesList) {
            if (srpRules.getNotSrp()) {
                throw new BizException("对不起,【" + killMail.getShipTypeName() + "】不支持补损");
            }
            if (!srpRules.getIsNpc() && killMail.getIsNpc()) {
                throw new BizException("对不起,【" + killMail.getShipTypeName() + "】不支持NPC击杀补损");
            }
            if (srpRules.getJoinTime() != null) {
                // 判断加团时间
                UserAccount userAccount = userAccountMapper.selectById(killMail.getAccountId());

                if (userAccount.getJoinTime() == null && !userAccount.getCorpId().equals(ConfigKit.get(CacheKey.EVE_MAIN_CORP,Integer.class))) {
                    throw new BizException("对不起,当前角色尚未加入主军团,无法进行补损");
                }

                if (userAccount.getJoinTime() != null) {
                    long num = DateUtil.betweenDay(userAccount.getJoinTime(), new Date(), false);
                    if(num > srpRules.getJoinTime()){
                        throw new BizException("对不起【" + killMail.getShipTypeName() + "】仅支持入团" + srpRules.getJoinTime() + "天的成员进行补损，您已入团" + num + "天");
                    }

                }
            }
        }

        srpLog.insert();
    }

    /**
     * 修改一个补损申请
     *
     * @param srpLog 补损申请
     */
    public void edit(SrpLog srpLog) {
        srpLog.updateById();
        srpLog = srpLog.selectById();
        AccountKillMail killMail = accountKillMailMapper.selectById(srpLog.getKillMailId());
        MailApi.send(userAccountService.getMainAccount(), "补损审批完成", EsiMailTemplate.getSrpNotice(srpLog, killMail), List.of(new MailApi.Recipient(killMail.getCharacterId(), Recipient.RecipientTypeEnum.CHARACTER)));
    }
}
