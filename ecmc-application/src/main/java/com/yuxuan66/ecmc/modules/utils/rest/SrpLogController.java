package com.yuxuan66.ecmc.modules.utils.rest;

import com.yuxuan66.ecmc.modules.utils.entity.SrpLog;
import com.yuxuan66.ecmc.modules.utils.entity.query.SrpLogQuery;
import com.yuxuan66.ecmc.modules.utils.service.SrpLogService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
@RestController
@RequestMapping(path = "/srpLog")
public class SrpLogController extends BaseController<SrpLogService> {

    /**
     * 分页查询补损提交记录
     * @param logQuery 查询条件
     * @return 补损提交记录
     */
    @GetMapping
    public Ps list(SrpLogQuery logQuery){
        return baseService.list(logQuery);
    }


    /**
     * 提交一条新的补损申请
     *
     * @param srpLog 补损记录
     */
    @PostMapping
    public Rs add(@RequestBody SrpLog srpLog) {
        baseService.add(srpLog);
        return Rs.ok();
    }

    /**
     * 修改一个补损申请
     * @param srpLog 补损申请
     */
    @PutMapping
    public Rs edit(@RequestBody SrpLog srpLog){
        baseService.edit(srpLog);
        return Rs.ok();
    }
}
