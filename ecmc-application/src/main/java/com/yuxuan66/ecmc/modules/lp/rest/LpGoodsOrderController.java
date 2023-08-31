package com.yuxuan66.ecmc.modules.lp.rest;

import com.yuxuan66.ecmc.modules.lp.entity.dto.OrderDto;
import com.yuxuan66.ecmc.modules.lp.entity.query.LpGoodsOrderQuery;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.*;
import com.yuxuan66.ecmc.modules.lp.service.LpGoodsOrderService;
/**
 * @author Sir丶雨轩
 * @since 2022/12/14
 */
@RestController
@RequestMapping(path = "/lpGoodsOrder")
public class LpGoodsOrderController extends BaseController<LpGoodsOrderService> {

    /**
     * 分页查询LP商品兑换订单
     * @param baseQuery 查询条件
     * @return 商品订单
     */
    @GetMapping
    public Ps list(LpGoodsOrderQuery baseQuery) {
        return baseService.list(baseQuery);
    }

    /**
     * 批量订单审批
     *
     * @param orderDto 订单信息
     */
    @PutMapping(path = "/batchApproval")
    public Rs batchApproval(@RequestBody OrderDto orderDto) {
        baseService.batchApproval(orderDto);
        return Rs.ok();
    }

}
