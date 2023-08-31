package com.yuxuan66.ecmc.modules.esi.rest;

import com.yuxuan66.ecmc.modules.esi.service.EsiService;
import com.yuxuan66.ecmc.support.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sir丶雨轩
 * @since 2022/12/12
 */
@Controller
@RequestMapping(path = "/esi")
public class EsiController extends BaseController<EsiService> {

    /**
     * 跳转至授权登录页面
     *
     * @param token 用户Token，此项不能为空
     * @return 授权登录地址
     */

    @GetMapping(path = "/auth/{token}")
    public String auth(@PathVariable String token) {
        return redirect(baseService.authPath(token));
    }

    /**
     * ESI授权回调
     *
     * @param code  esi code
     * @param state 用户token
     */
    @GetMapping(path = "/callback")
    public String callback(String code, String state) {
        return redirect(baseService.callback(code, state));
    }

}
