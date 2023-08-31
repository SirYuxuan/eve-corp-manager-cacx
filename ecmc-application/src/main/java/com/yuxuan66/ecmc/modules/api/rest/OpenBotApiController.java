package com.yuxuan66.ecmc.modules.api.rest;

import com.yuxuan66.ecmc.modules.bot.service.OpenBotService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sir丶雨轩
 * @since 2023/1/5
 */
@RestController
@RequestMapping(path = "/openBotApi")
public class OpenBotApiController extends BaseController<OpenBotService> {

    /**
     * 获取一个物品的价格
     * @param isEur 是否是欧服
     * @param isCol 是否是组查询
     * @param name 名称
     * @return 信息
     */
    @GetMapping(path = "/getPrice/{isEur}/{isCol}/{name}")
    public Rs getPrice(@PathVariable boolean isEur, @PathVariable boolean isCol, @PathVariable String name){
        return Rs.ok(baseService.getPrice(isEur, isCol, name));
    }
}
