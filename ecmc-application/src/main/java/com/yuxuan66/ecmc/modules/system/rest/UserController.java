package com.yuxuan66.ecmc.modules.system.rest;

import cn.hutool.core.map.MapUtil;
import com.yuxuan66.ecmc.common.utils.StpUtil;
import com.yuxuan66.ecmc.modules.system.entity.User;
import com.yuxuan66.ecmc.modules.system.entity.dto.LoginDto;
import com.yuxuan66.ecmc.modules.system.entity.dto.RegisterDto;
import com.yuxuan66.ecmc.modules.system.entity.dto.ResetPasswordDto;
import com.yuxuan66.ecmc.modules.system.entity.query.UserQuery;
import com.yuxuan66.ecmc.modules.system.service.UserService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * @author Sir丶雨轩
 * @since 2022/12/6
 */
@RequestMapping(path = "/user")
@RestController
public class UserController extends BaseController<UserService> {

    /**
     * 用户登录
     *
     * @param loginDto 登录信息
     * @return 标准返回
     */
    @PostMapping(path = "/login")
    public Rs login(@RequestBody LoginDto loginDto) {
        Map<String, Object> tokenInfo = MapUtil.newHashMap(1);
        tokenInfo.put("token", baseService.login(loginDto));
        return Rs.ok(tokenInfo);
    }



    /**
     * 退出登录
     */
    @GetMapping(path = "/logout")
    public void logout(){
        cn.dev33.satoken.stp.StpUtil.logout();
    }
    /**
     * 分页查询用户列表
     * @param userQuery 查询条件
     * @return 用户列表
     */
    @GetMapping
    public Ps list(UserQuery userQuery){
        return baseService.list(userQuery);
    }

    /**
     * 导出用户列表
     * @param userQuery 查询条件
     */
    @GetMapping(path = "/download")
    public void download(UserQuery userQuery){
        baseService.download(userQuery);
    }

    /**
     * 获取当前登录用户信息
     * @return 用户信息
     */
    @GetMapping(path = "/getUserInfo")
    public Rs getUserInfo(){
        return Rs.ok(baseService.getUserInfo());
    }

    /**
     * 获取当前登录用户的权限代码
     * @return 权限代码
     */
    @GetMapping(path = "/getPermCode")
    public Rs getPermCode(){
        return Rs.ok(baseService.getPermCode(StpUtil.getLoginId()));
    }

    /**
     * 判断指定字段是否有用户使用
     *
     * @param field 字段
     * @param value 数据
     * @param id    id
     * @return 是否使用
     */
    @GetMapping(path = "/checkFieldExist/{field}/{value}")
    public Rs checkFieldExist(@PathVariable String field,@PathVariable String value, Long id) {
        return Rs.ok(baseService.checkFieldExist(field,value,id));
    }



    /**
     * 添加一个用户
     *
     * @param user 用户
     */
    @PostMapping
    public Rs add(@RequestBody User user){
        baseService.add(user);
        return Rs.ok();
    }

    /**
     * 编辑一个用户
     * @param user 用户
     */
    @PutMapping
    public Rs edit(@RequestBody User user){
        baseService.edit(user);
        return Rs.ok();
    }


    /**
     * 编辑当前登录用户的基本信息
     * @param user 用户
     */
    @PutMapping(path = "/editLogin")
    public Rs editLogin(@RequestBody User user){
        baseService.editLogin(user);
        return Rs.ok();
    }

    /**
     * 删除用户
     * @param ids 用户id
     */
    @DeleteMapping
    public Rs del(@RequestBody Set<Long> ids){
        baseService.del(ids);
        return Rs.ok();
    }


    /**
     * 给指定邮件发送注册邮件
     *
     * @param email 邮件
     */
    @PutMapping(path = "/registerMail/{email}")
    public Rs registerMail(@PathVariable String email) {
        baseService.registerMail(email);
        return Rs.ok();
    }

    /**
     * 发送重置密码的邮件
     * @param account 登录账号或邮箱
     */
    @PutMapping(path = "/sendResetPasswordEmail/{account}")
    public Rs sendResetPasswordEmail(@PathVariable String account) {
        baseService.sendResetPasswordEmail(account);
        return Rs.ok();
    }
    /**
     * 用户密码重置
     * @param resetPasswordDto 重置密码的信息
     */
    @PutMapping(path = "/resetPassword")
    public Rs resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        baseService.resetPassword(resetPasswordDto);
        return Rs.ok();
    }

    /**
     * 注册一个军团系统用户
     *
     * @param registerDto 注册信息
     */
    @PostMapping(path = "/register")
    public Rs register(@RequestBody RegisterDto registerDto) {
        baseService.register(registerDto);
        return Rs.ok();
    }

    /**
     * 获取当前登录的用户
     * @return 用户
     */
    @GetMapping(path = "/getLoginUser")
    public Rs getLoginUser(){
        return Rs.ok(baseService.getLoginUser());
    }
}
