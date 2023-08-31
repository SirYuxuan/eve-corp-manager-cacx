package com.yuxuan66.ecmc.support.mail;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.mail.MailAccount;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.CacheKey;

/**
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
public class MailHelper {


    /**
     * 获取一个邮件发送账户
     * @return 账户
     */
    public static MailAccount defaultMailAccount() {
        MailAccount mailAccount = new MailAccount();
        mailAccount.setAuth(true);
        mailAccount.setSslEnable(true);
        mailAccount.setUser(ConfigKit.get(CacheKey.MAIL_USER));
        mailAccount.setFrom(ConfigKit.get(CacheKey.MAIL_FROM));
        mailAccount.setPass(ConfigKit.get(CacheKey.MAIL_PASS));
        mailAccount.setHost(ConfigKit.get(CacheKey.MAIL_HOST));
        mailAccount.setPort(ConfigKit.get(CacheKey.MAIL_PORT,Integer.class));
        return mailAccount;
    }
}
