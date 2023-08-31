package com.yuxuan66.ecmc.modules.discord.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.common.consts.Const;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.service.UserAccountService;
import com.yuxuan66.ecmc.modules.discord.api.DiscordApi;
import com.yuxuan66.ecmc.modules.discord.api.DiscordClient;
import com.yuxuan66.ecmc.modules.discord.api.entity.oauth.TokenResponse;
import com.yuxuan66.ecmc.modules.discord.api.entity.user.DiscordUserResponse;
import com.yuxuan66.ecmc.modules.discord.api.entity.user.GuildsMembers;
import com.yuxuan66.ecmc.modules.system.entity.User;
import com.yuxuan66.ecmc.modules.system.entity.UsersRoles;
import com.yuxuan66.ecmc.modules.system.mapper.UserMapper;
import com.yuxuan66.ecmc.modules.system.mapper.UsersRolesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/12
 */
@Service
@RequiredArgsConstructor
public class DiscordService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UsersRolesMapper usersRolesMapper;
    private final DiscordApi discordApi;
    private final DiscordClient discordClient;
    private final UserAccountService userAccountService;

    /**
     * 跳转至Discord授权地址
     *
     * @param token 用户登录token
     * @return 授权地址
     */
    public String auth(String token) {
        if (token == null) {
            return "";
        }
        return "https://discord.com/api/oauth2/authorize?client_id=1062561355036622869&redirect_uri=http%3A%2F%2Fdiscord.hd-eve.com%2Fdiscord%2Fcallback&response_type=code&scope=identify%20email%20connections%20guilds%20guilds.join%20gdm.join&state=" + token;
    }

    /**
     * Discord授权回调
     *
     * @param code code
     * @return 授权完地址
     */
    public String callback(String code, String state, Long guildId, Integer permissions) {
        TokenResponse tokenResponse = discordApi.getToken(code);
        User user = userMapper.selectById(Convert.toLong(state));
        user.setDiscordAccessToken(tokenResponse.getAccessToken());
        user.setDiscordRefreshToken(tokenResponse.getRefreshToken());
        user.setDiscordExpiresIn(DateUtil.offsetSecond(new Date(), tokenResponse.getExpiresIn()).toTimestamp());
        user.updateById();
        DiscordUserResponse usersMe = discordClient.usersMe(user);
        user.setDiscordId(usersMe.getId());
        user.setDiscordName(usersMe.getUsername());
        user.setDiscordEmail(usersMe.getEmail());
        user.updateById();
        // 需要给用户添加的角色组
        List<Long> roles = new ArrayList<>();
        // 当前用户所拥有的角色
        List<Long> roleIds = usersRolesMapper.selectList(new QueryWrapper<UsersRoles>().eq("user_id", user.getId())).stream().map(UsersRoles::getRoleId).toList();
        if(roleIds.contains(Const.CORP_ROLE)){
            roles.add(ConfigKit.get(CacheKey.ROLE_CORP_MEMBER,Long.class));
        }
        if(roleIds.contains(Const.DIRECTOR_ROLE)){
            roles.add(ConfigKit.get(CacheKey.ROLE_CORP_ADMIN,Long.class));
        }
        UserAccount account = userAccountService.getMainAccount(user.getId());
        String nickName = user.getNickName() + "/" + (account == null ? "未绑定角色" : account.getCharacterName());
        discordClient.addGuildsMembers(ConfigKit.get(CacheKey.CORP_GUILDS, Long.class), new GuildsMembers(user.getDiscordId(), user.getDiscordAccessToken(), nickName, roles, false, false));
        return "https://discord.com/channels/1030287560922103890/1030287561370906666";
    }

}
