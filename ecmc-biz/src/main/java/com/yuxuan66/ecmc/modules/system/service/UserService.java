package com.yuxuan66.ecmc.modules.system.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.Mail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.common.consts.Const;
import com.yuxuan66.ecmc.common.utils.*;
import com.yuxuan66.ecmc.modules.account.mapper.UserAccountMapper;
import com.yuxuan66.ecmc.modules.system.entity.Menu;
import com.yuxuan66.ecmc.modules.system.entity.Role;
import com.yuxuan66.ecmc.modules.system.entity.User;
import com.yuxuan66.ecmc.modules.system.entity.UsersRoles;
import com.yuxuan66.ecmc.modules.system.entity.consts.UserStatus;
import com.yuxuan66.ecmc.modules.system.entity.dto.LoginDto;
import com.yuxuan66.ecmc.modules.system.entity.dto.RegisterDto;
import com.yuxuan66.ecmc.modules.system.entity.dto.ResetPasswordDto;
import com.yuxuan66.ecmc.modules.system.entity.dto.UserInfoDto;
import com.yuxuan66.ecmc.modules.system.entity.query.UserQuery;
import com.yuxuan66.ecmc.modules.system.mapper.RoleMapper;
import com.yuxuan66.ecmc.modules.system.mapper.UserMapper;
import com.yuxuan66.ecmc.modules.system.mapper.UsersRolesMapper;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.exception.BizException;
import com.yuxuan66.ecmc.support.mail.MailHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Sir丶雨轩
 * @since 2022/12/6
 */
@Service
public class UserService extends BaseService<User, UserMapper> {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private UsersRolesMapper usersRolesMapper;

    /**
     * 用户登录
     *
     * @param loginDto 登录信息
     * @return token
     */
    public String login(LoginDto loginDto) {

        User user = query().eq("username", loginDto.getUsername()).one();
        // RSA解密前端传递密码
        // 密码校验
        if (user == null || !PasswordUtil.validatePassword(loginDto.getPassword(), user.getPassword())) {
            throw new BizException("用户名密码输入错误");
        }
        // 判断用户状态
        if (user.getStatus() != UserStatus.NORMAL) {
            throw new BizException("您的账户已经被" + user.getStatus().getName() + ", 请联系系统管理员.");
        }
        StpUtil.login(user);
        // 修改用户数据
        user.setLoginTime(Lang.getNowTimestamp());
        user.setLoginIp(WebUtil.getIp());
        try{
            user.setLoginCity(WebUtil.getCityInfo(user.getLoginIp()));
        }catch (Exception ignored){

        }
        user.updateById();

        return StpUtil.getTokenValue();
    }

    /**
     * 发送重置密码的邮件
     * @param account 登录账号或邮箱
     */
    public void sendResetPasswordEmail(String account){
        User user = query().eq("username", account).or().eq("email", account).one();
        if(user == null){
            throw new BizException("对不起,没有找到您的账号");
        }
        String code = RandomUtil.randomString(6).toUpperCase();
        InputStream in = new ClassPathResource("template/email/reset.html").getStream();
        Mail.create(MailHelper.defaultMailAccount())
                .setTos(user.getEmail())
                .setTitle("邮箱验证")
                .setContent(IoUtil.read(in, Charset.defaultCharset()).replace("{code}", code).replace("{time}", DateUtil.now()))
                .setHtml(true)
                .send();
        redis.hSet(CacheKey.EMAIL_CODE_RESET, user.getEmail(), code);
    }

    /**
     * 分页查询用户列表
     *
     * @param userQuery 查询条件
     * @return 用户列表
     */
    public Ps list(UserQuery userQuery) {
        List<User> userList = baseMapper.listUser(userQuery);
        for (User user : userList) {
            user.setRoleNames(String.join(",", user.getRoles().stream().map(Role::getName).toList()));
            user.setRoleIds(user.getRoles().stream().map(Role::getId).collect(Collectors.toList()));
        }
        return Ps.ok(baseMapper.countUser(userQuery), userList);
    }

    /**
     * 获取当前登录用户的详细信息
     *
     * @return 用户信息
     */
    public UserInfoDto getUserInfo() {
        UserInfoDto userInfoDto = new UserInfoDto();
        // 当前登录用户
        User user = StpUtil.getUser(User.class);
        BeanUtils.copyProperties(user, userInfoDto);
        userInfoDto.setUserId(user.getId());
        userInfoDto.setRealName(user.getNickName());
        userInfoDto.setRoles(roleMapper.selectRoleByUserId(user.getId()));
        return userInfoDto;
    }


    /**
     * 获取指定用户ID的权限代码
     *
     * @param userId 用户id
     * @return 权限代码
     */
    @Cacheable(cacheNames = "User:PermCode", key = "#userId")
    public List<String> getPermCode(Long userId) {
        User user = baseMapper.findUserById(userId);
        return user.getMenus().stream().map(Menu::getPermission).filter(Objects::nonNull).toList();
    }

    /**
     * 判断指定字段是否有用户使用
     *
     * @param field 字段
     * @param value 数据
     * @param id    id
     * @return 是否使用
     */
    public boolean checkFieldExist(String field, String value, Long id) {
        return query().eq(field, value).ne(id != null, "id", id).count() > 0;
    }

    /**
     * 添加一个用户
     *
     * @param user 用户
     */

    public void add(User user) {
        user.setPassword(PasswordUtil.createHash(user.getPassword()));
        user.insert();
        for (Long roleId : user.getRoleIds()) {
            new UsersRoles(user.getId(), roleId).insert();
        }
    }

    /**
     * 编辑一个用户
     *
     * @param user 用户
     */
    @CacheEvict(cacheNames = "User:PermCode", key = "#user.id", allEntries = true)
    public void edit(User user) {
        User old = getById(user.getId());
        if (!user.getPassword().equals(old.getPassword())) {
            user.setPassword(PasswordUtil.createHash(user.getPassword()));
        }
        usersRolesMapper.delete(new QueryWrapper<UsersRoles>().eq("user_id", user.getId()));
        for (Long roleId : user.getRoleIds()) {
            new UsersRoles(user.getId(), roleId).insert();
        }
        user.updateById();
    }

    /**
     * 编辑当前登录用户的基本信息
     * @param user 用户
     */
    public void editLogin(User user){
        user.setId(StpUtil.getLoginId());
        user.updateById();
        StpUtil.login(getById(user.getId()));
    }


    /**
     * 删除用户
     *
     * @param ids 用户id
     */
    public void del(Set<Long> ids) {
        usersRolesMapper.delete(new QueryWrapper<UsersRoles>().in("user_id", ids));
        removeBatchByIds(ids);
    }

    /**
     * 查询有指定权限的用户
     *
     * @param premCode 权限编码
     * @return 用户列表
     */
    public List<User> findByPermCode(String premCode) {
        return baseMapper.selectByPermCode(premCode);
    }


    /**
     * 导出用户列表
     *
     * @param userQuery 查询条件
     */
    public void download(UserQuery userQuery) {
        FileUtil.exportExcel(preDownload(baseMapper.listUser(userQuery)));
    }

    /**
     * 给指定邮件发送注册邮件
     *
     * @param email 邮件
     */
    public void registerMail(String email) {
        String code = RandomUtil.randomString(6).toUpperCase();
        InputStream in = new ClassPathResource("template/email/register.html").getStream();
        Mail.create(MailHelper.defaultMailAccount())
                .setTos(email)
                .setTitle("邮箱验证")
                .setContent(IoUtil.read(in, Charset.defaultCharset()).replace("{code}", code).replace("{time}", DateUtil.now()))
                .setHtml(true)
                .send();
        redis.hSet(CacheKey.EMAIL_CODE, email, code);
    }

    /**
     * 用户密码重置
     * @param resetPasswordDto 重置密码的信息
     */
    public void resetPassword(ResetPasswordDto resetPasswordDto){
        User user = query().eq("username", resetPasswordDto.getAccount()).or().eq("email", resetPasswordDto.getAccount()).one();
        if(user == null){
            throw new BizException("对不起,没有找到您的账号");
        }
        // 判断邮箱验证码是否正确
        String emailCode = redis.hGet(CacheKey.EMAIL_CODE_RESET, user.getEmail());
        if (!emailCode.equalsIgnoreCase(resetPasswordDto.getSms())) {
            throw new BizException("邮箱验证码输入错误");
        }
        redis.del(CacheKey.EMAIL_CODE_RESET, user.getEmail());
        // 开始重置密码
        user.setPassword(PasswordUtil.createHash(resetPasswordDto.getPassword()));
        user.updateById();

    }

    /**
     * 注册一个军团系统用户
     *
     * @param registerDto 注册信息
     */
    public void register(RegisterDto registerDto) {
        // 判断邮箱验证码是否正确
        String emailCode = redis.hGet(CacheKey.EMAIL_CODE, registerDto.getEmail());
        if (!emailCode.equalsIgnoreCase(registerDto.getCode())) {
            throw new BizException("邮箱验证码输入错误");
        }
        redis.del(CacheKey.EMAIL_CODE, registerDto.getEmail());
        // 判断用户名是否存在
        boolean exist = checkFieldExist("username", registerDto.getUsername(), null);
        if (exist) {
            throw new BizException("用户名已存在,请重新输入");
        }
        exist = checkFieldExist("email", registerDto.getEmail(), null);
        if (exist) {
            throw new BizException("邮箱已存在,请重新输入");
        }
        // 开始创建用户
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setCity("未知");
        user.setEmail(registerDto.getEmail());
        user.setPassword(PasswordUtil.createHash(registerDto.getPassword()));
        user.setNickName("无名氏");
        user.insert();
        // 绑定注册用户权限组
        UsersRoles usersRoles = new UsersRoles(user.getId(), Const.DEFAULT_ROLE);
        usersRoles.insert();

    }

    /**
     * 获取当前登录的用户
     * @return 用户
     */
    public User getLoginUser(){
        return getById(StpUtil.getLoginId());
    }

}
