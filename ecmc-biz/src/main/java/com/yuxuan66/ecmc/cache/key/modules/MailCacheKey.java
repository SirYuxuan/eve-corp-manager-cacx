package com.yuxuan66.ecmc.cache.key.modules;

/**
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
public interface MailCacheKey {

    /**
     * 邮件发送用户
     */
     String MAIL_USER = "mail.user";

    /**
     * 邮件发送密码
     */
     String MAIL_PASS = "mail.pass";
    /**
     * 邮件发送人
     */
     String MAIL_FROM = "mail.from";
    /**
     * 邮件发送服务器地址
     */
     String MAIL_HOST = "mail.host";
    /**
     * 邮件服务器端口
     */
     String MAIL_PORT = "mail.port";
}
