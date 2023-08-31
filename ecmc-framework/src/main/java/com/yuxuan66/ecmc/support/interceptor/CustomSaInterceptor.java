package com.yuxuan66.ecmc.support.interceptor;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;

/**
 * @author Sir丶雨轩
 * @since 2022/12/6
 */
public class CustomSaInterceptor extends SaInterceptor {

    /**
     * 自定义SaToken的权限拦截
     */
    public CustomSaInterceptor() {
        super(handler -> {
            SaRouter.match("/**")
                    .notMatch("/user/login")
                    .notMatch("/user/resetPassword")
                    .notMatch("/user/registerMail/**")
                    .notMatch("/user/sendResetPasswordEmail/**")
                    .notMatch("/user/register")
                    .notMatch("/*.*")
                    // 放开公开的API
                    .notMatch("/openApi/**")
                    .notMatch("/corpUser/checkQQ")
                    // Discord授权回调
                    .notMatch("/discord/**")
                    // 价格查询
                    .notMatch("/openBotApi/getPrice/**/**/**")
                    .notMatch("/openAccountApi/**/**")
                    // 放开ESI授权和回调
                    .notMatch("/esi/**")
                    .notMatch("/attach/upload")
                    .check(r -> StpUtil.checkLogin());
        });
    }
}
