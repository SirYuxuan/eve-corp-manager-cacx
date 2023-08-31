package com.yuxuan66.ecmc.modules.data.rest;

import com.yuxuan66.ecmc.modules.data.service.SdeService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sir丶雨轩
 * @since 2022/12/25
 */
@RestController
@RequestMapping(path = "/sde")
public class SdeController extends BaseController<SdeService> {
    /**
     * 模糊查询舰船列表
     *
     * @param shipName 舰船名称
     * @return 舰船列表
     */
    @GetMapping(path = "/shipList/{shipName}")
    public Rs shipList(@PathVariable String shipName) {
        return Rs.ok(baseService.shipList(shipName));
    }

}