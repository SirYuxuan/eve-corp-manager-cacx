package com.yuxuan66.ecmc.cache.key.modules;

/**
 * @author Sir丶雨轩
 * @since 2023/1/11
 */
public interface DiscordCacheKey {

    /**
     * discord 应用 public key
     */
    String PUBLIC_KEY = "discord.public.key";
    /**
     * discord 应用 client secret
     */
    String CLIENT_SECRET = "discord.client.secret";

    /**
     * discord 应用 client id
     */
    String CLIENT_ID = "discord.client.id";
    /**
     * discord 授权回调
     */
    String REDIRECT_URI = "discord.redirect.uri";
    /**
     * discord 机器人 token
     */
    String BOT_TOKEN = "discord.bot.token";
    /**
     * 军团军团ID
     */
    String CORP_GUILDS = "discord.corp.guilds";
    /**
     * 军团成员对应的DiscordID
     */
    String ROLE_CORP_MEMBER = "discord.role.corp.member";
    /**
     * 军团总监对应的DiscordID
     */
    String ROLE_CORP_ADMIN = "discord.role.corp.admin";


}
