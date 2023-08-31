package com.yuxuan66.ecmc.modules.system.rest;

import com.yuxuan66.ecmc.modules.system.entity.Config;
import com.yuxuan66.ecmc.modules.system.service.ConfigService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
@RestController
@RequestMapping(path = "/config")
public class ConfigController extends BaseController<ConfigService> {
    /**
     * 分页查询系统配置列表
     *
     * @param query 查询条件
     * @return 系统配置
     */
    @GetMapping
    public Ps list(BaseQuery<Config> query) {
        return baseService.list(query);
    }

    /**
     * 添加一个配置
     *
     * @param config 配置对象
     */
    @PostMapping
    public Rs add(@RequestBody Config config) {
        baseService.addOrEdit(config);
        return Rs.ok();
    }

    /**
     * 编辑一个配置
     *
     * @param config 配置对象
     */
    @PutMapping
    public Rs edit(@RequestBody Config config) {
        baseService.addOrEdit(config);
        return Rs.ok();
    }

    /**
     * 删除一组配置
     *
     * @param ids 配置ID列表
     */
    @DeleteMapping
    public Rs del(@RequestBody Set<Long> ids) {
        baseService.del(ids);
        return Rs.ok();
    }

    /**
     * 刷新系统内所有缓存
     */
    @PutMapping(path = "/refresh")
    public Rs refresh() {
        baseService.refresh();
        return Rs.ok();
    }

}
