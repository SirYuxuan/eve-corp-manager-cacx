package com.yuxuan66.ecmc.job.modules;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.common.utils.Lang;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.UserAccountMapper;
import com.yuxuan66.ecmc.modules.data.entity.Member;
import com.yuxuan66.ecmc.modules.data.mapper.MemberMapper;
import com.yuxuan66.ecmc.modules.system.entity.User;
import com.yuxuan66.ecmc.modules.system.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.troja.eve.esi.api.CorporationApi;
import net.troja.eve.esi.model.CorporationMemberTrackingResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/24
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class CorpMemberJob  {

    @Resource
    private UserMapper userMapper;
    private final EveCache eveCache;
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private UserAccountMapper userAccountMapper;

    @Scheduled(cron = "0 58 * ? * *")
    public void process( ) throws Exception {
        UserAccount userAccount = userAccountMapper.selectById(1);
        userAccount.esiClient();
        CorporationApi corporationApi = new CorporationApi();
        List<CorporationMemberTrackingResponse> memberTracking = corporationApi.getCorporationsCorporationIdMembertracking(userAccount.getCorpId(), "", "", userAccount.getAccessToken());
        List<Member> memberList = new ArrayList<>();
        List<Integer> notSeatMember = getNotSeatMember();
        List<User> userList = userMapper.selectList(null);
        List<UserAccount> userAccountList = userAccountMapper.selectList(null);
        for (CorporationMemberTrackingResponse memberTrackingResponse : memberTracking) {
            Member member = new Member();
            member.setCharacterId(memberTrackingResponse.getCharacterId());
            member.setCharacterName(eveCache.getCharacterInfo(member.getCharacterId()).getName());
            // 判断是否在军团系统中存在
            List<UserAccount> accountList = userAccountList.stream().filter(item -> item.getCharacterId().equals(memberTrackingResponse.getCharacterId())).toList();
            if (accountList.isEmpty()) {
                member.setCorpSystem(false);
            } else {
                member.setCorpSystem(true);
                member.setAccountId(accountList.get(0).getId());
                member.setUserId(accountList.get(0).getUserId());
                member.setCharacterName(accountList.get(0).getCharacterName());
                userList.stream().filter(item -> item.getId().equals(member.getUserId())).findFirst().ifPresent(item -> member.setNickName(item.getNickName()));
            }
            // 获取上次登录时间
            OffsetDateTime logoffDate = memberTrackingResponse.getLogoffDate();
            if (logoffDate != null) {
                member.setLastLoginTime(Lang.get(logoffDate));
                member.setNotLoginDay((int) DateUtil.betweenDay(new Date(member.getLastLoginTime().getTime()), new Date(), true));
            }
            // 判断是否在SEAT中存在
            member.setSeatSystem(!notSeatMember.contains(member.getCharacterId()));
            memberList.add(member);

        }
        memberMapper.delete(null);
        memberMapper.batchInsert(memberList);
    }

    /**
     * 获取没有在SEAT中的用户
     *
     * @return 用户列表
     */
    public List<Integer> getNotSeatMember() {
        HttpRequest request = HttpUtil.createGet("https://seat.winterco.space/corporation/view/tracking/98598862/membertracking?draw=3&columns%5B0%5D%5Bdata%5D=refresh_token&columns%5B0%5D%5Bname%5D=user.refresh_token&columns%5B0%5D%5Bsearchable%5D=false&columns%5B0%5D%5Borderable%5D=false&columns%5B0%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B0%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B1%5D%5Bdata%5D=character_id&columns%5B1%5D%5Bname%5D=character_id&columns%5B1%5D%5Bsearchable%5D=true&columns%5B1%5D%5Borderable%5D=true&columns%5B1%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B1%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B2%5D%5Bdata%5D=location&columns%5B2%5D%5Bname%5D=location&columns%5B2%5D%5Bsearchable%5D=true&columns%5B2%5D%5Borderable%5D=true&columns%5B2%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B2%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B3%5D%5Bdata%5D=start_date&columns%5B3%5D%5Bname%5D=start_date&columns%5B3%5D%5Bsearchable%5D=false&columns%5B3%5D%5Borderable%5D=true&columns%5B3%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B3%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B4%5D%5Bdata%5D=logon_date&columns%5B4%5D%5Bname%5D=logon_date&columns%5B4%5D%5Bsearchable%5D=false&columns%5B4%5D%5Borderable%5D=true&columns%5B4%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B4%5D%5Bsearch%5D%5Bregex%5D=false&order%5B0%5D%5Bcolumn%5D=0&order%5B0%5D%5Bdir%5D=desc&start=0&length=1000&search%5Bvalue%5D=&search%5Bregex%5D=false&selected_refresh_token_status=missing_users&_=1671865308372");
        request.cookie(ConfigKit.get(CacheKey.SEAT_COOKIE));
        request.header("referer", "https://seat.winterco.space/corporation/view/tracking/98598862");
        request.header("x-requested-with", "XMLHttpRequest");
        JSONArray array = JSONObject.parseObject(request.execute().body()).getJSONArray("data");
        List<Integer> characterIdList = new ArrayList<>();
        for (Object o : array) {
            JSONObject obj = (JSONObject) o;
            String characterId = obj.getString("character_id");
            characterIdList.add(Convert.toInt(ReUtil.get("characters/(.*)/portrait", characterId, 1)));
        }
        return characterIdList;
    }
}
