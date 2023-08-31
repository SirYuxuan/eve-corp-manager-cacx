package com.yuxuan66.ecmc.modules.lp.rest;

import com.yuxuan66.ecmc.modules.lp.entity.LpGoods;
import com.yuxuan66.ecmc.modules.lp.entity.dto.BuyLpGoodsDto;
import com.yuxuan66.ecmc.modules.lp.service.LpGoodsService;
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
@RequestMapping(path = "/lpGoods")
@RestController
public class LpGoodsController extends BaseController<LpGoodsService> {

    /**
     * 分页查询LP商品列表
     *
     * @param query 查询条件
     * @return 商品列表
     */
    @GetMapping
    public Ps list(BaseQuery<LpGoods> query) {
        return baseService.list(query);
    }

    /**
     * 添加一个LP商品
     *
     * @param lpGoods LP商品
     */
    @PostMapping
    public Rs add(@RequestBody LpGoods lpGoods) {
        baseService.addOrEdit(lpGoods);
        return Rs.ok();
    }

    /**
     * 修改一个LP商品
     *
     * @param lpGoods LP商品
     */
    @PutMapping
    public Rs edit(@RequestBody LpGoods lpGoods) {
        baseService.addOrEdit(lpGoods);
        return Rs.ok();
    }

    /**
     * 购买一个LP商品
     *
     * @param lpGoodsDto 购买信息
     */
    @PutMapping(path = "/buyGoods")
    public Rs buyGoods(@RequestBody BuyLpGoodsDto lpGoodsDto) {
        baseService.buyGoods(lpGoodsDto);
        return Rs.ok();
    }


    /**
     * 批量删除一组LP商品
     *
     * @param ids LP商品ID列表
     */
    @DeleteMapping
    public Rs del(@RequestBody Set<Long> ids) {
        baseService.del(ids);
        return Rs.ok();
    }
}
