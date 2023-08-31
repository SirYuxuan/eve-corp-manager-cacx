package com.yuxuan66.ecmc.modules.account.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Address;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.common.consts.Const;
import com.yuxuan66.ecmc.common.utils.Lang;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.AccountSkillQueueMapper;
import com.yuxuan66.ecmc.modules.account.mapper.AccountWalletMapper;
import com.yuxuan66.ecmc.modules.account.mapper.UserAccountMapper;
import com.yuxuan66.ecmc.modules.system.entity.User;
import com.yuxuan66.ecmc.modules.system.entity.UsersRoles;
import com.yuxuan66.ecmc.modules.system.mapper.UserMapper;
import com.yuxuan66.ecmc.modules.system.mapper.UsersRolesMapper;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.exception.BizException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import net.troja.eve.esi.api.SkillsApi;
import net.troja.eve.esi.api.WalletApi;
import net.troja.eve.esi.model.CharacterSkillqueueResponse;
import net.troja.eve.esi.model.CharacterSkillsResponse;
import net.troja.eve.esi.model.CharacterWalletJournalResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

/**
 * 公开用户API
 *
 * @author Sir丶雨轩
 * @since 2022/12/24
 */
@Service
public class OpenAccountApiService extends BaseService<UserAccount, UserAccountMapper> {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UsersRolesMapper usersRolesMapper;
    @Resource
    private AccountWalletMapper accountWalletMapper;

    @Resource
    private AccountSkillQueueMapper accountSkillQueueMapper;
    private final EveCache eveCache;
    private final Map<Integer, Type> typeMap;

    public OpenAccountApiService(EveCache eveCache) {
        this.eveCache = eveCache;
        typeMap = eveCache.getTypeMap();
    }

    /**
     * 返回包含主页的字符串
     *
     * @param message 消息
     * @return 结果
     */
    private String withSite(String message) {
        return message + " \r" + ConfigKit.get(CacheKey.WEB_SITE);
    }

    /**
     * 查询指定QQ的刷怪信息
     *
     * @param qq QQ号
     * @return 刷怪信息
     */
    @SneakyThrows
    public String rat(long qq) {

        AccountInfo accountList = getAccountByQQ(qq);
        Date now = new Date();
        DateTime day30 = DateUtil.offsetDay(now, -30);
        DateTime day7 = DateUtil.offsetDay(now, -7);
        DateTime day = DateUtil.beginOfDay(now);

        // 查询30天内的刷怪记录
        // List<AccountWallet> accountWalletList = accountWalletMapper.selectList(new QueryWrapper<AccountWallet>().eq("user_id", accountList.getUserId()).ge("date", day30).eq("ref_type", "bounty_prizes"));


        StringBuilder result = new StringBuilder();
        result.append(accountList.getNickName()).append(",刷怪信息:\r");
        double dayRatTotal = 0;
        double day7RatTotal = 0;
        double day30RatTotal = 0;
        double day30TaxTotal = 0;
        for (UserAccount account : accountList.getAccountList()) {
            account.esiClient();
            WalletApi walletApi = new WalletApi();
            try {
                List<CharacterWalletJournalResponse> walletJournal = walletApi.getCharactersCharacterIdWalletJournal(account.getCharacterId(), "", "", 1, account.getAccessToken());
                // 当前角色数据
                List<CharacterWalletJournalResponse> meAccountWalletList = walletJournal.stream().filter(item -> item.getRefType() == CharacterWalletJournalResponse.RefTypeEnum.BOUNTY_PRIZES && Objects.equals(item.getSecondPartyId(), account.getCharacterId())).toList();
                // 本日刷怪信息
                RatTaxMoney dayMoney = getAccountWalletList(day, meAccountWalletList);
                dayRatTotal += dayMoney.getRatMoney();
                // 7天内刷怪量
                RatTaxMoney day7Money = getAccountWalletList(day7, meAccountWalletList);
                day7RatTotal += day7Money.getRatMoney();
                // 30天内刷怪量
                RatTaxMoney day30Money = getAccountWalletList(day30, meAccountWalletList);
                day30RatTotal += day30Money.getRatMoney();
                day30TaxTotal += day30Money.getTaxMoney();

                result.append(account.getCharacterName()).append("\r");
                // 30天都没有记录
                if (day30Money.getRatMoney() <= 0) {
                    result.append("此角色无刷怪记录\r");
                    continue;
                }
                result.append("今天: ").append(Lang.getFormatNumber(dayMoney.getRatMoney() / 1000000)).append("M ISK\r");
                result.append("7天 : ").append(Lang.getFormatNumber(day7Money.getRatMoney() / 1000000)).append("M ISK\r");
                result.append("30天: ").append(Lang.getFormatNumber(day30Money.getRatMoney() / 1000000)).append("M ISK\r");
            } catch (Exception e) {
                result.append(account.getCharacterName()).append("->此角色授权失效\r");
            }
        }
        result.append("总计\r\n");
        result.append("今天: ").append(Lang.getFormatNumber(dayRatTotal / 1000000)).append("M ISK\r");
        result.append("7天 : ").append(Lang.getFormatNumber(day7RatTotal / 1000000)).append("M ISK\r");
        result.append("30天: ").append(Lang.getFormatNumber(day30RatTotal / 1000000)).append("M ISK\r");
        result.append("30天共纳税: ").append(Lang.getFormatNumber(day30TaxTotal / 1000000)).append("M ISK\r");
        result.append("雨轩机器人温馨提醒: 快刷怪,多交税");
        return result.toString();
    }

    /**
     * 查询指定QQ的LP信息
     *
     * @param qq QQ
     * @return LP信息
     */
    public String lp(long qq) {
        AccountInfo accountInfo = getAccountByQQ(qq);
        String result = accountInfo.getNickName() + " 您的LP统计如下:\r\n";
        result += "共获得: " + accountInfo.getAccountList().stream().map(UserAccount::getLpTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        result += "\r\n已使用: " + accountInfo.getAccountList().stream().map(UserAccount::getLpUse).reduce(BigDecimal.ZERO, BigDecimal::add);
        result += "\r\n现剩余: " + accountInfo.getAccountList().stream().map(UserAccount::getLpNow).reduce(BigDecimal.ZERO, BigDecimal::add);
        result += "\r\n感谢您为混沌做出的贡献!";
        return result;
    }

    /**
     * 判断QQ是否属于军团成员
     *
     * @param qq QQ
     * @return 状态
     */
    public boolean checkQQ(String qq) {
        List<User> userList = userMapper.selectList(new QueryWrapper<User>().in("qq", qq.split(",")));
        if (userList.isEmpty()) {
            return false;
        }
        List<Long> roleIds = usersRolesMapper.selectList(new QueryWrapper<UsersRoles>().in("user_id", userList.stream().map(User::getId).toList())).stream().map(UsersRoles::getRoleId).toList();
        return roleIds.contains(Const.CORP_ROLE);
    }


    /**
     * 获取一个QQ的基础信息
     *
     * @param qq qq
     * @return 基础信息
     */
    @SneakyThrows
    public String info(long qq) {
        AccountInfo accountInfo = getAccountByQQ(qq);
        StringBuilder result = new StringBuilder();
        result.append(accountInfo.getNickName()).append(",角色信息:\r");
        long skill = 0;
        long isk = 0;
        for (UserAccount userAccount : accountInfo.getAccountList()) {
            try {
                userAccount.esiClient();
                WalletApi walletApi = new WalletApi();
                Double wallet = walletApi.getCharactersCharacterIdWallet(userAccount.getCharacterId(), "", "", userAccount.getAccessToken());
                isk += wallet;
                SkillsApi skillsApi = new SkillsApi();

                CharacterSkillsResponse skillsResponse = skillsApi.getCharactersCharacterIdSkills(userAccount.getCharacterId(), "", "", userAccount.getAccessToken());
                skill += skillsResponse.getTotalSp();

                result.append(userAccount.getCharacterName()).append(":\r");
                result.append("技能点数: ").append(Lang.getFormatNumber(skillsResponse.getTotalSp())).append("\r");
                if (qq == 1718018032L) {
                    result.append("ISK: ").append("已破产, 快挂合同给我\r");
                } else {
                    result.append("ISK: ").append(Lang.getFormatNumber(wallet / 1000000)).append(" M ISK\r");
                }

                List<CharacterSkillqueueResponse> skillQueue = skillsApi.getCharactersCharacterIdSkillqueue(userAccount.getCharacterId(), "", "", userAccount.getAccessToken());
                if (skillQueue.isEmpty()) {
                    result.append("当前暂无技能队列\r");
                    continue;
                }

                result.append("当前学习技能: ").append(typeMap.containsKey(skillQueue.get(0).getSkillId()) ? typeMap.get(skillQueue.get(0).getSkillId()).getName() : "未知技能,ID: " + skillQueue.get(0).getSkillId()).append("\r");
                OffsetDateTime finishDate = skillQueue.get(skillQueue.size() - 1).getFinishDate();
                result.append("技能队列结束时间: ").append(finishDate == null ? "海枯石烂" : Lang.get(finishDate)).append("\r");
            } catch (Exception e) {
                result.append(userAccount.getCharacterName()).append(" ,角色授权失效.").append(e.getMessage()).append("\r");
            }


        }
        result.append("===============\r总资产:\r");
        result.append("===============\r数据更新时间:");
        result.append(DateUtil.now());
        result.append("\r技能点数: ").append(Lang.getFormatNumber(skill)).append("\r");
        if (qq == 1718018032L) {
            result.append("ISK: ").append("已破产, 收购一切可交易物资\r");
        } else {
            result.append("ISK: ").append(Lang.getFormatNumber(isk / 1000000)).append(" M ISK\r");
        }
        return result.toString();
    }

    /**
     * 获取一个QQ的PAP信息
     *
     * @param qq qq
     * @return PAP信息
     */
    public String pap(long qq) {
        AccountInfo accountInfo = getAccountByQQ(qq);
        Map<String, String> papMap = getPap();
        int thisMonth = DateUtil.thisMonth() + 1;
        StringBuilder result = new StringBuilder(accountInfo.getNickName() + " 您的" + thisMonth + "月份PAP统计如下:\r\n");
        double papTotal = 0;
        for (UserAccount userAccount : accountInfo.getAccountList()) {
            double pap = Convert.toDouble(papMap.get(Convert.toStr(userAccount.getCharacterId())), 0D);
            if (pap == 0) {
                continue;
            }
            papTotal += pap;
            result.append(userAccount.getCharacterName()).append("\t\t").append(pap).append(" PAP\r\n");
        }

        result.append("您在").append(thisMonth).append("月份的总PAP为").append(papTotal).append(",请继续努力");
        return result.toString();
    }

    /**
     * 翻译一个EVE的名词
     *
     * @param name 名词
     * @return 翻译后
     */
    public String translate(String name) {
        for (Type eveType : eveCache.getTypeList()) {
            if (name.equals(eveType.getName())) {
                return eveType.getNameEn();
            }
            if (name.equalsIgnoreCase(eveType.getNameEn())) {
                return eveType.getName();
            }
        }
        // 去地名找一找
        for (Address eveAddress : eveCache.getAddressList()) {
            if (name.equals(eveAddress.getName())) {
                return eveAddress.getNameEn();
            } else if (name.equalsIgnoreCase(eveAddress.getNameEn())) {
                return eveAddress.getName();
            }
        }
        return "无法在游戏中找到【" + name + "】,我们也不想自动翻译";
    }

    /**
     * 根据QQ获取账号
     *
     * @param qq qq
     * @return 账号
     */
    public AccountInfo getAccountByQQ(long qq) throws BizException {
        List<User> userList = userMapper.selectList(new QueryWrapper<User>().eq("qq", qq));
        if (userList.isEmpty()) {
            throw new BizException(withSite("没有找账号信息,请先注册军团网站并绑定QQ."));
        }
        List<UserAccount> accountList = query().in("user_id", userList.stream().map(User::getId).toList()).list();
        if (accountList.isEmpty()) {
            throw new BizException(withSite("您注册了军团系统, 但没有绑定角色."));
        }
        return new AccountInfo(userList.get(0).getNickName(), userList.get(0).getId(), accountList);
    }

    /**
     * 过滤指定日期的钱包记录
     *
     * @param startTime           开始时间
     * @param meAccountWalletList 钱包记录
     */
    private RatTaxMoney getAccountWalletList(DateTime startTime, List<CharacterWalletJournalResponse> meAccountWalletList) {
        List<CharacterWalletJournalResponse> day7WalletList = meAccountWalletList.stream().filter(item -> Lang.get(item.getDate()).getTime() > startTime.getTime()).toList();
        double ratMoney = day7WalletList.stream().mapToDouble(item -> item.getAmount() == null ? 0 : item.getAmount()).sum();
        double taxMoney = day7WalletList.stream().mapToDouble(item -> item.getTax() == null ? 0 : item.getTax()).sum();
        return new RatTaxMoney(ratMoney, taxMoney);
    }


    private Map<String, String> getPap() {
        HttpRequest request = HttpUtil.createGet("https://seat.winterco.space/character/view/paps/2117284154?division=monthly&month=" + (DateUtil.thisMonth() + 1) + "&year=" + DateUtil.thisYear() + "&_=" + System.currentTimeMillis());
        request.cookie(ConfigKit.get(CacheKey.SEAT_COOKIE));
        request.header("referer", "https://seat.winterco.space/character/view/paps/2117284154");
        request.header("x-requested-with", "XMLHttpRequest");
        String body = request.execute().body();
        Map<String, String> map = new HashMap<>();
        if (JSONUtil.isTypeJSON(body)) {
            JSONArray array = JSON.parseObject(body).getJSONArray("data");
            for (Object o : array) {
                JSONObject data = (JSONObject) o;
                map.put(ReUtil.get("characters/(.*)/portrait", data.getString("character_id"), 1), data.getString("qty"));
            }
        }
        return map;
    }

    @Data
    @AllArgsConstructor
    public static class AccountInfo {
        private String nickName;
        private Long userId;
        private List<UserAccount> accountList;
    }

    @Data
    @AllArgsConstructor
    public static class RatTaxMoney {
        private double ratMoney;
        private double taxMoney;
    }
}
