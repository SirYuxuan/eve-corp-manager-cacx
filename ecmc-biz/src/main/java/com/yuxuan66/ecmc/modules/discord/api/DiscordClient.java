package com.yuxuan66.ecmc.modules.discord.api;

import com.dtflys.forest.annotation.*;
import com.dtflys.forest.http.ForestResponse;
import com.yuxuan66.ecmc.modules.discord.api.entity.user.DiscordUserResponse;
import com.yuxuan66.ecmc.modules.discord.api.entity.user.GuildsMembers;
import com.yuxuan66.ecmc.modules.discord.api.forest.annotation.BotToken;
import com.yuxuan66.ecmc.modules.discord.api.forest.annotation.Token;
import com.yuxuan66.ecmc.modules.system.entity.User;

/**
 * Discord Client
 *
 * @author Sir丶雨轩
 * @since 2023/1/11
 */
//p3(client = ProxyHttpClientProvider.class)
@BaseRequest(baseURL = "{url}")
public interface DiscordClient {

    /**
     * 获取当前用户对于的Discord用户
     *
     * @param user 用户
     * @return discord用户
     */
    @Token
    @Get("users/@me")
    DiscordUserResponse usersMe(User user);


    /**
     * 将一个用户邀请至指定频道
     *
     * @param guildId  频道ID
     * @param guildsMembers 参数信息
     * @return 绑定信息
     */
    @BotToken
    @Put("/guilds/{guildId}/members/{guildsMembers.userId}")
    ForestResponse<String> addGuildsMembers(@Var("guildId") Long guildId,@Var("guildsMembers") @JSONBody GuildsMembers guildsMembers);
}
