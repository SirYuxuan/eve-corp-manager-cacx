package com.yuxuan66.ecmc.modules.data.rest;

import com.yuxuan66.ecmc.modules.utils.entity.SrpRules;
import com.yuxuan66.ecmc.modules.utils.service.SrpRulesService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Sir丶雨轩
 * @since 2022/12/25
 */
@RestController
@RequestMapping(path = "/srpRules")
public class SrpRulesController extends BaseController<SrpRulesService> {
    /**
     * 分页查询SRP规则
     *
     * @param query 查询条件
     * @return SRP规则
     */
    @GetMapping
    public Ps list(BaseQuery<SrpRules> query) {
        return baseService.list(query);
    }

    /**
     * 新增一个补损规则
     *
     * @param srpRules 补损规则
     */
    @PostMapping
    public Rs add(@RequestBody SrpRules srpRules) {
        baseService.addOrEdit(srpRules);
        return Rs.ok();
    }

    /**
     * 编辑一个补损规则
     *
     * @param srpRules 补损规则
     */
    @PutMapping
    public Rs edit(@RequestBody SrpRules srpRules) {
        baseService.addOrEdit(srpRules);
        return Rs.ok();
    }

    /**
     * 批量删除一组补损规则
     *
     * @param ids 规则ID
     */
    @DeleteMapping
    public Rs del(@RequestBody Set<Long> ids) {
        baseService.del(ids);
        return Rs.ok();
    }

}
