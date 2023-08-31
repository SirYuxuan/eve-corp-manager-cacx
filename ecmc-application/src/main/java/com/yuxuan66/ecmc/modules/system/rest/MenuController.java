package com.yuxuan66.ecmc.modules.system.rest;

import com.yuxuan66.ecmc.modules.system.entity.Menu;
import com.yuxuan66.ecmc.modules.system.service.MenuService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


/**
 * @author Sir丶雨轩
 * @since 2022/12/9
 */
@RequestMapping(path = "/menu")
@RestController
public class MenuController extends BaseController<MenuService> {

    /**
     * 构建前台需要的菜单树
     * @return 菜单树
     */
    @GetMapping(path = "/build")
    public Rs build(){
        return Rs.ok(baseService.build());
    }

    /**
     * 查询整个菜单树
     * @return 菜单树
     */
    @GetMapping(path = "/tree")
    public Rs tree(){
        return Rs.ok(baseService.tree());
    }

    /**
     * 添加一个菜单
     * @param menu 菜单
     */
    @PostMapping
    public Rs add(@RequestBody Menu menu){
        baseService.addOrEdit(menu);
        return Rs.ok();
    }

    /**
     * 编辑一个菜单
     * @param menu 菜单
     */
    @PutMapping
    public Rs edit(@RequestBody Menu menu){
        baseService.addOrEdit(menu);
        return Rs.ok();
    }

    /**
     * 删除一组菜单
     * @param ids 菜单id列表
     */
    @DeleteMapping
    public Rs del(@RequestBody Set<Long> ids){
        baseService.del(ids);
        return Rs.ok();
    }
}
