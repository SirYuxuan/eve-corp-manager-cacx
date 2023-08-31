package com.yuxuan66.ecmc.modules.lp.rest;

import com.yuxuan66.ecmc.modules.lp.entity.dto.SendLpDto;
import com.yuxuan66.ecmc.modules.lp.entity.query.LpLogQuery;
import com.yuxuan66.ecmc.modules.lp.service.LpService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
@RestController
@RequestMapping(path = "/lp")
public class LpController extends BaseController<LpService> {


    /**
     * 给指定用户发放LP
     *
     * @param sendLpDto 发放信息
     */
    @PostMapping(path = "/sendLP")
    public Rs sendLp(@RequestBody SendLpDto sendLpDto) {
        baseService.sendLp(sendLpDto);
        return Rs.ok();
    }

    /**
     * 查询指定角色或用户的LP历史
     * @param lpLogQuery 查询条件
     * @return LP历史
     */
    @GetMapping(path = "/listLpLogByAccountId")
    public Ps listLpLogByAccountId(LpLogQuery lpLogQuery) {
        return Ps.ok(baseService.listLpLogByAccountId(lpLogQuery));
    }

    /**
     * 分页查询LP发放记录，如果有发放的权限则查看所有，否则查看自己的
     *
     * @param lpLogQuery 查询条件
     * @return LP历史
     */
    @GetMapping(path = "/listLpLog")
    public Ps listLpLog(LpLogQuery lpLogQuery) {
        return Ps.ok(baseService.listLpLog(lpLogQuery));
    }
}
