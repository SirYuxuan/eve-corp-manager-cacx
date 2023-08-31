package com.yuxuan66.ecmc.modules.system.rest;

import com.yuxuan66.ecmc.modules.system.entity.Role;
import com.yuxuan66.ecmc.modules.system.entity.dto.SaveMenuDto;
import com.yuxuan66.ecmc.modules.system.service.RoleService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


/**
 * @author Sir丶雨轩
 * @since 2022/12/9
 */
@RequestMapping(path = "/role")
@RestController
public class RoleController extends BaseController<RoleService> {

    /**
     * 查询系统内所有角色列表
     * @return 角色列表
     */
    @GetMapping(path = "/all")
    public Rs all(){
        return Rs.ok(baseService.all());
    }
    /**
     * 分页查询角色列表
     * @param query 查询条件
     * @return 角色列表
     */
    @GetMapping
    public Ps list(BaseQuery<Role> query){
        return baseService.list(query);
    }
    /**
     * 保存角色菜单
     * @param saveMenuDto 保存数据
     */
    @PutMapping(path = "/saveMenu")
    public Rs saveMenu(@RequestBody SaveMenuDto saveMenuDto){
        baseService.saveMenu(saveMenuDto);
        return Rs.ok();
    }

    /**
     * 新增一个角色
     * @param role 角色信息
     */
    @PostMapping
    public Rs add(@RequestBody Role role){
        baseService.addOrEdit(role);
        return Rs.ok();
    }

    /**
     * 编辑角色信息
     * @param role 角色信息
     */
    @PutMapping
    public Rs edit(@RequestBody Role role){
        baseService.addOrEdit(role);
        return Rs.ok();
    }

    /**
     * 删除角色
     * @param ids 角色id
     */
    @DeleteMapping
    public Rs del(@RequestBody Set<Long> ids){
        baseService.del(ids);
        return Rs.ok();
    }
}
