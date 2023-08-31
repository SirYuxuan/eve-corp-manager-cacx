package com.yuxuan66.discord.modules.auth.rest;

import com.yuxuan66.ecmc.modules.discord.service.DiscordService;
import com.yuxuan66.ecmc.support.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sir丶雨轩
 * @since 2023/1/11
 */
@Controller
@RequestMapping(path = "/discord")
public class DiscordController extends BaseController<DiscordService> {

    /**
     * 进入Discord授权地址
     * @param token 用户token
     * @return 授权地址
     */
    @GetMapping(path = "/auth")
    public String auth(String token){
        return redirect(baseService.auth(token));
    }

    /**
     * Discord授权回调
     *
     * @param code code
     * @return 授权完地址
     */
    @GetMapping(path = "/callback")
    public String callback(String code,String state,Long guild_id,Integer permissions){
        return redirect(baseService.callback(code, state,guild_id,permissions));
    }


}
