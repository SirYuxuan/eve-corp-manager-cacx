package com.yuxuan66.ecmc.modules.data.rest;

import com.yuxuan66.ecmc.modules.utils.entity.SrpBlacklist;
import com.yuxuan66.ecmc.modules.utils.service.SrpBlacklistService;
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
@RequestMapping(path = "/srpBlacklist")
public class SrpBlacklistController extends BaseController<SrpBlacklistService> {
    /**
     * 分页查询SRP黑名单
     *
     * @param query 查询条件
     * @return SRP黑名单
     */
    @GetMapping
    public Ps list(BaseQuery<SrpBlacklist> query) {
        return baseService.list(query);
    }

    /**
     * 新增一个补损黑名单
     *
     * @param srpBlacklist 补损黑名单
     */
    @PostMapping
    public Rs add(@RequestBody SrpBlacklist srpBlacklist) {
        baseService.addOrEdit(srpBlacklist);
        return Rs.ok();
    }

    /**
     * 编辑一个补损黑名单
     *
     * @param srpBlacklist 补损黑名单
     */
    @PutMapping
    public Rs edit(@RequestBody SrpBlacklist srpBlacklist) {
        baseService.addOrEdit(srpBlacklist);
        return Rs.ok();
    }

    /**
     * 批量删除一组补损黑名单
     *
     * @param ids 黑名单ID
     */
    @DeleteMapping
    public Rs del(@RequestBody Set<Long> ids) {
        baseService.del(ids);
        return Rs.ok();
    }

}
