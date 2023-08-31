package com.yuxuan66.ecmc.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.modules.system.entity.Role;
import com.yuxuan66.ecmc.modules.system.entity.RolesMenus;
import com.yuxuan66.ecmc.modules.system.entity.dto.SaveMenuDto;
import com.yuxuan66.ecmc.modules.system.mapper.RoleMapper;
import com.yuxuan66.ecmc.modules.system.mapper.RolesMenusMapper;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Sir丶雨轩
 * @since 2022/12/9
 */
@Service
public class RoleService extends BaseService<Role, RoleMapper> {
    @Resource
    private RolesMenusMapper rolesMenusMapper;

    /**
     * 查询系统内所有角色列表
     *
     * @return 角色列表
     */
    public List<Role> all() {
        return query().list();
    }


    /**
     * 分页查询角色列表
     *
     * @param query 查询条件
     * @return 角色列表
     */
    public Ps list(BaseQuery<Role> query) {
        return Ps.ok(baseMapper.countRole(query), baseMapper.listRole(query));
    }

    /**
     * 新增或编辑角色,编辑角色时清除系统内所有用户的权限缓存
     * @param role 角色信息
     */
    @CacheEvict(cacheNames = "User:PermCode",condition = "#role.id != null",allEntries = true)
    public void addOrEdit(Role role){
        role.insertOrUpdate();
    }

    /**
     * 删除角色
     * @param ids 角色id
     */
    @CacheEvict(cacheNames = "User:PermCode",allEntries = true)
    public void del(Set<Long> ids){
        rolesMenusMapper.delete(new QueryWrapper<RolesMenus>().in("role_id",ids));
        removeBatchByIds(ids);
    }

    /**
     * 保存角色菜单
     *
     * @param saveMenuDto 保存数据
     */
    @CacheEvict(cacheNames = "User:PermCode",allEntries = true)
    public void saveMenu(SaveMenuDto saveMenuDto) {
        rolesMenusMapper.delete(new QueryWrapper<RolesMenus>().eq("role_id", saveMenuDto.getRoleId()));
        List<RolesMenus> rolesMenusList = new ArrayList<>();
        for (Long menuId : saveMenuDto.getMenuIds()) {
            rolesMenusList.add(new RolesMenus(saveMenuDto.getRoleId(), menuId, false));
        }
        for (Long menuId : saveMenuDto.getVirtuallyMenuIds()) {
            rolesMenusList.add(new RolesMenus(saveMenuDto.getRoleId(), menuId, true));
        }
        if (!rolesMenusList.isEmpty()) {
            rolesMenusMapper.batchInsert(rolesMenusList);
        }

    }

}
