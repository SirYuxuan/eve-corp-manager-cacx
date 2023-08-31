package com.yuxuan66.ecmc.modules.api.rest;

import com.yuxuan66.ecmc.modules.api.service.OpenApiService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Sir丶雨轩
 * @since 2022/12/9
 */
@RequestMapping(path = "/openApi")
@RestController
public class OpenApiController extends BaseController<OpenApiService> {


    /**
     * 获取每日一言的句子
     *
     * @return 每日一言
     */
    @GetMapping(path = "/oneDay")
    public Rs oneDay() {
        return Rs.ok(baseService.oneDay());
    }

}
