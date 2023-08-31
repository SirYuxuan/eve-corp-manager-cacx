package com.yuxuan66.ecmc.modules.account.rest;

import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.entity.query.UserAccountQuery;
import com.yuxuan66.ecmc.modules.account.service.UserAccountService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Sir丶雨轩
 * @since 2022/12/12
 */
@RequestMapping(path = "/userAccount")
@RestController
public class UserAccountController extends BaseController<UserAccountService> {

    /**
     * 添加或更新一个账户，根据授权Code
     *
     * @param code 授权Code
     */
    @PostMapping(path = "/addOrUpdateAccount/{code}")
    public Rs addOrUpdateAccount(@PathVariable String code) {
        baseService.addOrUpdateAccount(code);
        return Rs.ok();
    }


    /**
     * 分页查询角色列表
     * @param baseQuery 查询条件
     * @return 角色列表
     */
    @GetMapping
    public Ps list(BaseQuery<UserAccount> baseQuery) {
        return baseService.list(baseQuery);
    }

    /**
     * 获取主页的统计数据
     * @return 统计数据
     */
    @GetMapping(path = "/getHomeData")
    public Rs getHomeData() {
        return Rs.ok(baseService.getHomeData());
    }

    /**
     * 获取统计页面上方的表Grid数据
     *
     * @return 数据
     */
    @GetMapping(path = "/analysisGrid/{all}")
    public Rs analysisGrid(@PathVariable boolean all) {
        return Rs.ok(baseService.analysisGrid(all));
    }

    /**
     * 分页查全部角色列表,用户LP发放
     * @param accountQuery 查询条件
     * @return 角色列表
     */
    @GetMapping(path = "/listAll")
    public Ps listAll(UserAccountQuery accountQuery) {
        return baseService.listAll(accountQuery);
    }

    /**
     * 查询当前登录用户的所有角色
     *
     * @return 所有角色列表
     */
    @GetMapping(path = "/all")
    public Rs all(){
        return Rs.ok(baseService.all());
    }

    /**
     * 获取登录用户当前可使用的LP
     * @return LP数量
     */
    @GetMapping(path ="/getNowLp")
    public Rs getNowLp(){
        return Rs.ok(baseService.getNowLp());
    }

    /**
     * 获取当前登录用户的KM记录
     * @return KM记录
     */
    @GetMapping(path = "/listKillMail")
    public Rs listKillMail(){
        return Rs.ok(baseService.listKillMail());
    }

    /**
     * 获取一个击毁报告的详细信息
     * @param killMailId 击毁报告ID
     * @return 详细信息
     */
    @GetMapping(path = "/listKillMailItem/{killMailId}")
    public Rs listKillMailItem(@PathVariable Long killMailId){
        return Rs.ok(baseService.listKillMailItem(killMailId));
    }

    /**
     * 导出角色列表
     * @param baseQuery 查询条件
     */
    @GetMapping(path = "/download")
    public void download(BaseQuery<UserAccount> baseQuery) {
         baseService.download(baseQuery);
    }


    /**
     * 设置主角色
     * @param id 角色记录ID
     */
    @PutMapping(path = "/setMainAccount/{id}")
    public Rs setMainAccount(@PathVariable Long id) {
        baseService.setMainAccount(id);
        return Rs.ok();
    }
    /**
     * 批量删除角色列表
     * @param ids 角色id列表
     */
    @DeleteMapping
    public Rs del(@RequestBody Set<Long> ids){
        baseService.del(ids);
        return Rs.ok();
    }

    /**
     * 刷新当前登录用户的所有角色KM信息
     */
    @PutMapping(path = "/refreshKm")
    public Rs refreshKm(){
        baseService.refreshKm();
        return Rs.ok();
    }



    /**
     * 根据角色名模糊搜索角色列表
     * @param characterName 角色名
     * @return 角色列表
     */
    @GetMapping(path = "/listAccount/{characterName}")
    public Rs listAccount(@PathVariable String characterName){
        return Rs.ok(baseService.listAccount(characterName));
    }
}
