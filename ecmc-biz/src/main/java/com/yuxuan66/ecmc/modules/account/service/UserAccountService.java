package com.yuxuan66.ecmc.modules.account.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.common.consts.Const;
import com.yuxuan66.ecmc.common.esi.EsiHelper;
import com.yuxuan66.ecmc.common.utils.FileUtil;
import com.yuxuan66.ecmc.common.utils.Lang;
import com.yuxuan66.ecmc.common.utils.StpUtil;
import com.yuxuan66.ecmc.modules.account.entity.*;
import com.yuxuan66.ecmc.modules.account.entity.query.UserAccountQuery;
import com.yuxuan66.ecmc.modules.account.mapper.*;
import com.yuxuan66.ecmc.modules.account.service.refresh.AccountKillMailRefresh;
import com.yuxuan66.ecmc.modules.lp.entity.LpLog;
import com.yuxuan66.ecmc.modules.lp.entity.consts.LpType;
import com.yuxuan66.ecmc.modules.lp.mapper.LpLogMapper;
import com.yuxuan66.ecmc.modules.system.entity.UsersRoles;
import com.yuxuan66.ecmc.modules.system.mapper.UsersRolesMapper;
import com.yuxuan66.ecmc.modules.system.service.UserService;
import com.yuxuan66.ecmc.modules.utils.entity.SrpLog;
import com.yuxuan66.ecmc.modules.utils.mapper.SrpLogMapper;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.exception.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.troja.eve.esi.ApiException;
import net.troja.eve.esi.api.*;
import net.troja.eve.esi.auth.JWT;
import net.troja.eve.esi.auth.OAuth;
import net.troja.eve.esi.model.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Sir丶雨轩
 * @since 2022/12/12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserAccountService extends BaseService<UserAccount, UserAccountMapper> {

    private final UserService userService;
    @Resource
    private LpLogMapper lpLogMapper;
    @Resource
    private SrpLogMapper srpLogMapper;
    @Resource
    private UsersRolesMapper usersRolesMapper;
    @Resource
    private AccountSkillMapper accountSkillMapper;
    @Resource
    private AccountKillMailMapper accountKillMailMapper;
    @Resource
    private AccountOrderMapper accountOrderMapper;
    @Resource
    private AccountSaleOrderMapper accountSaleOrderMapper;
    @Resource
    private AccountWalletMapper accountWalletMapper;
    @Resource
    private AccountKillMailItemMapper accountKillMailItemMapper;
    private final AccountKillMailRefresh accountKillMailRefresh;
    private final EveCache eveCache;

    /**
     * 获取主页的统计数据
     *
     * @return 统计数据
     */
    public Map<String, Object> getHomeData() {
        List<UserAccount> userAccountList = query().eq("user_id", StpUtil.getLoginId()).list();
        double iskTotal = 0D;
        long skill = 0L;
        BigDecimal lpTotal = BigDecimal.ZERO;
        for (UserAccount account : userAccountList) {
            iskTotal += account.getIsk();
            lpTotal = lpTotal.add(account.getLpNow());
            skill += account.getSkill();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("isk", iskTotal);
        result.put("skill", skill);
        result.put("lp", lpTotal);
        result.put("pap", "暂无统计");
        return result;
    }

    /**
     * 获取统计页面上方的表Grid数据
     *
     * @return 数据
     */
    public Map<String, Object> analysisGrid(boolean all) {
        if (all && !userService.getPermCode(StpUtil.getLoginId()).contains("analysis:all")) {
            throw new BizException("您没有权限查询所有人数据");
        }

        Map<String, Object> result = new HashMap<>(6);
        Date beginMonth = DateUtil.offsetDay(new Date(), -30);
        Date beginDay = DateUtil.beginOfDay(new Date());
        // 获取市场收入
        List<AccountOrder> orderList = accountOrderMapper.selectList(new QueryWrapper<AccountOrder>().eq(!all, "user_id", StpUtil.getLoginId()).ge("date", beginMonth).orderByAsc("date"));
        result.put("orderPrice", orderList.stream().filter(item -> !item.getIsBuy()).mapToDouble(item -> item.getUnitPrice() * item.getQuantity()).sum());
        result.put("orderPriceDay", orderList.stream().filter(item -> !item.getIsBuy() && item.getDate().getTime() > beginDay.getTime()).mapToDouble(item -> item.getUnitPrice() * item.getQuantity()).sum());
        // 获取在售订单
        List<AccountSaleOrder> saleOrderList = accountSaleOrderMapper.selectList(new QueryWrapper<AccountSaleOrder>().eq(!all, "user_id", StpUtil.getLoginId()));
        result.put("saleOrderPrice", saleOrderList.stream().filter(item -> item.getIsBuyOrder() == null).mapToDouble(item -> item.getPrice() * item.getVolumeRemain()).sum());
        result.put("saleOrderPriceBuy", saleOrderList.stream().filter(item -> item.getIsBuyOrder() != null && item.getIsBuyOrder()).mapToDouble(item -> item.getPrice() * item.getVolumeRemain()).sum());
        // 获取刷怪量
        List<AccountWallet> walletList = accountWalletMapper.selectList(new QueryWrapper<AccountWallet>().eq(!all, "user_id", StpUtil.getLoginId()).eq("ref_type", "bounty_prizes").ge("date", beginMonth).orderByAsc("date"));
        result.put("ratPrice", walletList.stream().mapToDouble(AccountWallet::getAmount).sum());
        result.put("ratPriceDay", walletList.stream().filter(item -> item.getDate().getTime() > beginDay.getTime()).mapToDouble(AccountWallet::getAmount).sum());

        // 获取交易每日收入
        Map<String, List<AccountOrder>> orderMap = orderList.stream().collect(Collectors.groupingBy(item -> DateUtil.formatDate(item.getDate()), LinkedHashMap::new, Collectors.toList()));

        List<String> orderTimeList = new ArrayList<>();
        List<Object> orderPriceList = new ArrayList<>();
        List<Object> orderPriceBuyList = new ArrayList<>();
        orderMap.forEach((key, value) -> {
            orderTimeList.add(key);
            orderPriceList.add(value.stream().filter(item -> !item.getIsBuy()).mapToDouble(item -> item.getUnitPrice() * item.getQuantity()).sum());
            orderPriceBuyList.add(value.stream().filter(AccountOrder::getIsBuy).mapToDouble(item -> item.getUnitPrice() * item.getQuantity()).sum());
        });

        result.put("orderTimeList", orderTimeList);
        result.put("orderPriceList", orderPriceList);
        result.put("orderPriceBuyList", orderPriceBuyList);

        // 获取刷怪每日收入
        Map<String, List<AccountWallet>> ratMap = walletList.stream().collect(Collectors.groupingBy(item -> DateUtil.formatDate(item.getDate()), LinkedHashMap::new, Collectors.toList()));
        List<String> ratTimeList = new ArrayList<>();
        List<Object> ratPriceList = new ArrayList<>();
        ratMap.forEach((key, value) -> {
            ratTimeList.add(key);
            ratPriceList.add(value.stream().mapToDouble(AccountWallet::getAmount).sum());
        });

        result.put("ratTimeList", ratTimeList);
        result.put("ratPriceList", ratPriceList);

        return result;
    }

    /**
     * 获取查询条件
     *
     * @param baseQuery 基础条件
     * @return 查询条件
     */
    public QueryWrapper<UserAccount> getWrapper(BaseQuery<UserAccount> baseQuery) {
        baseQuery.processingCreateTime();
        baseQuery.processingBlurry("character_name", "corp_name", "alliance_name");
        QueryWrapper<UserAccount> wrapper = baseQuery.getQueryWrapper();
        wrapper.eq("user_id", StpUtil.getLoginId());
        wrapper.orderByDesc("corp_id");
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    /**
     * 分页查询角色列表
     *
     * @param baseQuery 查询条件
     * @return 角色列表
     */
    public Ps list(BaseQuery<UserAccount> baseQuery) {
        return Ps.ok(baseMapper.selectPage(baseQuery.getPage(), getWrapper(baseQuery)));
    }

    /**
     * 分页查全部角色列表,用户LP发放
     *
     * @param accountQuery 查询条件
     * @return 角色列表
     */
    public Ps listAll(UserAccountQuery accountQuery) {
        if (!accountQuery.isAll()) {
            accountQuery.setUserId(StpUtil.getLoginId());
        }

        return Ps.ok(baseMapper.listUserAccount(accountQuery.getPageOrder(), accountQuery));
    }

    /**
     * 查询当前登录用户的所有角色
     *
     * @return 所有角色列表
     */
    public List<UserAccount> all() {
        return query().eq("user_id", StpUtil.getLoginId()).list();
    }

    /**
     * 导出角色列表
     *
     * @param baseQuery 查询条件
     */
    public void download(BaseQuery<UserAccount> baseQuery) {
        FileUtil.exportExcel(preDownload(baseMapper.selectList(getWrapper(baseQuery))));
    }

    /**
     * 设置主角色
     *
     * @param id 角色记录ID
     */
    public void setMainAccount(Long id) {
        update(new UpdateWrapper<UserAccount>().ne("id", id).eq("user_id", StpUtil.getLoginId()).set("is_main", false));
        update(new UpdateWrapper<UserAccount>().eq("id", id).set("is_main", true));
    }

    /**
     * 批量删除角色列表
     *
     * @param ids 角色id列表
     */
    public void del(Set<Long> ids) {
        removeBatchByIds(ids);
    }

    /**
     * 根据角色名模糊搜索角色列表
     *
     * @param characterName 角色名
     * @return 角色列表
     */
    public List<UserAccount> listAccount(String characterName) {
        return query().like("character_name", characterName).or().eq("character_id", Convert.toLong(characterName, -1L)).list();
    }

    /**
     * 添加或更新一个账户，根据授权Code
     *
     * @param code 授权Code
     */
    public void addOrUpdateAccount(String code) {
        try {
            OAuth oAuth = EsiHelper.defaultOAuth();
            oAuth.finishFlow(code, "", ConfigKit.get(CacheKey.EVE_ESI_SECRET_KEY));
            JWT authJwt = oAuth.getJWT();
            int characterId = authJwt.getPayload().getCharacterID();
            String characterName = authJwt.getPayload().getName();
            List<UserAccount> accountList = query().eq("character_id", characterId).list();
            UserAccount userAccount;
            if (accountList.isEmpty()) {
                // 这个角色在系统中不存在，创建新的用户
                userAccount = new UserAccount();
                userAccount.setUserId(StpUtil.getLoginId());
                userAccount.setCharacterId(characterId);
                userAccount.setCharacterName(characterName);
                // 如果一个角色不存在，那么第一个角色即为主角色
                userAccount.setIsMain(query().eq("user_id", StpUtil.getLoginId()).count() == 0);
                userAccount.setAccessToken(oAuth.getAccessToken());
                userAccount.setRefreshToken(oAuth.getRefreshToken());
                userAccount.insert();
            } else {
                // 角色已经存在，直接刷新角色信息
                userAccount = accountList.get(0);
                if (!userAccount.getUserId().equals(StpUtil.getLoginId())) {
                    throw new BizException("此角色已被别的账号绑定，高老板说禁止重复绑定！");
                }
                userAccount.setUserId(StpUtil.getLoginId());
                userAccount.setCharacterId(characterId);
                userAccount.setCharacterName(characterName);
                userAccount.setAccessToken(oAuth.getAccessToken());
                userAccount.setRefreshToken(oAuth.getRefreshToken());
            }
            refresh(userAccount);

        } catch (ApiException e) {
            throw new BizException(StrUtil.format("无法获取用户信息：{}", e.getMessage()));
        }

    }

    /**
     * 给一个军团角色设置加团时间
     *
     * @param userAccount 角色
     * @throws ApiException API错误
     */
    public UserAccount setAccountJoinTime(UserAccount userAccount) throws ApiException {
        log.info("开始更新用户【{}】的加团时间",userAccount.getCharacterName());
        // 角色加团时间
        List<CharacterCorporationHistoryResponse> historyResponses = new CharacterApi().getCharactersCharacterIdCorporationhistory(userAccount.getCharacterId(), "", "");
        Optional<CharacterCorporationHistoryResponse> responseOptional = historyResponses.stream().filter(item -> item.getCorporationId().equals(Convert.toInt(ConfigKit.get(CacheKey.EVE_MAIN_CORP)))).findFirst();
        if (responseOptional.isPresent()) {
            OffsetDateTime startDate = responseOptional.get().getStartDate();
            userAccount.setJoinTime(Lang.get(startDate));
            userAccount.updateById();
        }
        return userAccount;
    }

    /**
     * 刷新一个角色的基本信息
     *
     * @param userAccount 角色
     */
    @Async("threadPoolTaskExecutor")
    public void refresh(UserAccount userAccount) {
        try {
            Map<Integer, Type> typeMap = eveCache.getTypeMap();
            userAccount.esiClient();
            // 角色军团联盟ID
            CharacterApi characterApi = new CharacterApi();
            CharacterResponse characterResponse = characterApi.getCharactersCharacterId(userAccount.getCharacterId(), "", "");
            userAccount.setCorpId(characterResponse.getCorporationId());
            userAccount.setAllianceId(characterResponse.getAllianceId());
            // 角色军团名称
            if (userAccount.getCorpId() != null) {
                CorporationApi corporationApi = new CorporationApi();
                CorporationResponse corporationResponse = corporationApi.getCorporationsCorporationId(userAccount.getCorpId(), "", "");
                userAccount.setCorpName(corporationResponse.getName());
            }
            // 联盟数据
            if (userAccount.getAllianceId() != null) {
                AllianceApi allianceApi = new AllianceApi();
                AllianceResponse allianceResponse = allianceApi.getAlliancesAllianceId(userAccount.getAllianceId(), "", "");
                userAccount.setAllianceName(allianceResponse.getName());
            }
            // 角色钱包余额
            WalletApi walletApi = new WalletApi();
            Double wallet = walletApi.getCharactersCharacterIdWallet(userAccount.getCharacterId(), "", "", userAccount.getAccessToken());
            userAccount.setIsk(wallet);
            // 角色技能点
            SkillsApi skillsApi = new SkillsApi();
            CharacterSkillsResponse skillsResponse = skillsApi.getCharactersCharacterIdSkills(userAccount.getCharacterId(), "", "", userAccount.getAccessToken());
            userAccount.setSkill(skillsResponse.getTotalSp());
            userAccount.setUnallocatedSp(skillsResponse.getUnallocatedSp() == null ? 0 : skillsResponse.getUnallocatedSp());
            // 处理已完成技能
            List<Skill> skillList = skillsResponse.getSkills();
            List<AccountSkill> accountSkillList = new ArrayList<>(skillList.size());
            for (Skill skill : skillList) {
                AccountSkill accountSkill = new AccountSkill();
                accountSkill.setAccountId(userAccount.getId());
                accountSkill.setUserId(userAccount.getUserId());
                accountSkill.setCharacterId(userAccount.getCharacterId());
                accountSkill.setCharacterName(userAccount.getCharacterName());
                accountSkill.setSkillId(skill.getSkillId());
                accountSkill.setSkillName(typeMap.containsKey(skill.getSkillId()) ? typeMap.get(skill.getSkillId()).getName() : "未知技能,ID: " + skill.getSkillId());
                accountSkill.setActiveSkillLevel(skill.getActiveSkillLevel());
                accountSkill.setTrainedSkillLevel(skill.getTrainedSkillLevel());
                accountSkill.setSkillPointsInSkill(skill.getSkillpointsInSkill());
                accountSkillList.add(accountSkill);
            }
            accountSkillMapper.delete(new QueryWrapper<AccountSkill>().eq("account_id", userAccount.getId()));
            if (!accountSkillList.isEmpty()) {
                accountSkillMapper.batchInsert(accountSkillList);
            }
            // 设置加团时间
            setAccountJoinTime(userAccount);
            // 判断是否是军团成员
            if (userAccount.getCorpId().equals(ConfigKit.get(CacheKey.EVE_MAIN_CORP, Integer.class))) {
                // 主军团成员，判断用户是否有军团成员的权限
                Long count = usersRolesMapper.selectCount(new QueryWrapper<UsersRoles>().eq("user_id", userAccount.getUserId()).eq("role_id", Const.CORP_ROLE));
                if (count == 0) {
                    UsersRoles corpUsersRoles = new UsersRoles(userAccount.getUserId(), Const.CORP_ROLE);
                    corpUsersRoles.insert();
                }
            }
            // TODO 判断是否跟联盟有蓝加声望
            // 开始纠正用户LP数量
            UpdateWrapper<LpLog> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("character_name", userAccount.getCharacterName());
            updateWrapper.set("user_id", userAccount.getUserId());
            updateWrapper.set("account_id", userAccount.getId());
            lpLogMapper.update(new LpLog(), updateWrapper);
            List<LpLog> lpLogList = lpLogMapper.selectList(new QueryWrapper<LpLog>().eq("character_name", userAccount.getCharacterName()));
            BigDecimal expenditure = lpLogList.stream().filter(item -> item.getType() == LpType.EXPENDITURE).map(LpLog::getLp).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal income = lpLogList.stream().filter(item -> item.getType() == LpType.INCOME).map(LpLog::getLp).reduce(BigDecimal.ZERO, BigDecimal::add);
            // 收入-支出 = 当前LP
            userAccount.setLpNow(income.subtract(expenditure));
            // 收入 = 总LP
            userAccount.setLpTotal(income);
            // 支出 = 使用的LP
            userAccount.setLpUse(expenditure);
            updateById(userAccount);
            log.info("用户[" + userAccount.getCharacterName() + "]的基础信息刷新成功," + userAccount);
        } catch (ApiException e) {
            log.warn("用户[" + userAccount.getCharacterName() + "]的基本信息刷新失败");
            log.error(e.getResponseBody(), e);
        }
    }


    /**
     * 获取当前登录用户的主角色
     *
     * @return 主角色
     */
    public UserAccount getMainAccount(Long... userId) {
        return getOne(new QueryWrapper<UserAccount>().eq("user_id", userId.length == 1 ? userId[0] : StpUtil.getLoginId()).eq("is_main", true));
    }

    /**
     * 获取登录用户当前可使用的LP
     *
     * @return LP数量
     */
    public BigDecimal getNowLp() {
        return query().eq("user_id", StpUtil.getLoginId()).list().stream().map(UserAccount::getLpNow).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 获取当前登录用户的KM记录
     *
     * @return KM记录
     */
    public List<AccountKillMail> listKillMail() {
        List<Long> kmList = srpLogMapper.selectList(new QueryWrapper<SrpLog>().eq("user_id", StpUtil.getLoginId()).select("kill_mail_id")).stream().map(SrpLog::getKillMailId).toList();
        List<AccountKillMail> accountKillMails = accountKillMailMapper.selectList(new QueryWrapper<AccountKillMail>().eq("user_id", StpUtil.getLoginId()).orderByDesc("kill_mail_time"));
        for (AccountKillMail killMail : accountKillMails) {
            killMail.setSrp(kmList.contains(killMail.getId()));
        }
        return accountKillMails;
    }

    /**
     * 获取一个击毁报告的详细信息
     *
     * @param killMailId 击毁报告ID
     * @return 详细信息
     */
    public Map<String, Object> listKillMailItem(Long killMailId) {
        Map<String, Object> result = new HashMap<>(2);
        result.put("data", accountKillMailMapper.selectById(killMailId));
        result.put("list", accountKillMailItemMapper.selectList(new QueryWrapper<AccountKillMailItem>().eq("kill_mail_id", killMailId)));
        return result;
    }

    /**
     * 刷新当前登录用户的所有角色KM信息
     */
    public void refreshKm(){
        List<UserAccount> userAccountList = query().eq("user_id", StpUtil.getLoginId()).list();
        Map<Integer, Type> typeMap = eveCache.getTypeMap();
        List<AccountKillMail> killMailList = accountKillMailMapper.selectList(null);
        for (UserAccount userAccount : userAccountList) {
            accountKillMailRefresh.refresh(userAccount, typeMap, killMailList);
        }
    }

}
