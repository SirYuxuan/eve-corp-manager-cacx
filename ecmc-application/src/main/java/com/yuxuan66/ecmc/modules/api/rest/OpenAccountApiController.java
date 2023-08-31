package com.yuxuan66.ecmc.modules.api.rest;

import com.yuxuan66.ecmc.modules.account.service.OpenAccountApiService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sir丶雨轩
 * @since 2022/12/24
 */
@RestController
@RequestMapping(path = {"/openAccountApi","/corpUser"})
public class OpenAccountApiController extends BaseController<OpenAccountApiService> {

    /**
     * 查询指定QQ的刷怪信息
     *
     * @param qq QQ号
     * @return 刷怪信息
     */
    @GetMapping(path = "/rat/{qq}")
    public Rs rat(@PathVariable long qq) {
        return Rs.ok(baseService.rat(qq));
    }

    /**
     * 获取一个QQ的基础信息
     *
     * @param qq qq
     * @return 基础信息
     */
    @GetMapping(path = "/info/{qq}")
    public Rs info(@PathVariable long qq) {
        return Rs.ok(baseService.info(qq));
    }


    /**
     * 判断QQ是否属于军团成员
     * @param qqs QQ
     * @return 状态
     */
    @GetMapping(path = "/checkQQ")
    public Rs checkQQ(String qqs) {
        return Rs.ok(baseService.checkQQ(qqs));
    }

    /**
     * 查询指定QQ的LP信息
     *
     * @param qq QQ
     * @return LP信息
     */
    @GetMapping(path = "/lp/{qq}")
    public Rs lp(@PathVariable long qq) {
        return Rs.ok(baseService.lp(qq));
    }
    /**
     * 获取一个QQ的PAP信息
     *
     * @param qq qq
     * @return PAP信息
     */
    @GetMapping(path = "/pap/{qq}")
    public Rs pap(@PathVariable long qq) {
        return Rs.ok(baseService.pap(qq));
    }

    /**
     * 翻译一个EVE的名词
     * @param name 名词
     * @return 翻译后
     */
    @GetMapping(path = "/tr/{name}")
    public Rs tr(@PathVariable String name) {
        return Rs.ok(baseService.translate(name));
    }
}
