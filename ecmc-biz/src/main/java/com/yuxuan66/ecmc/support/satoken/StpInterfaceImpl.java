package com.yuxuan66.ecmc.support.satoken;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.convert.Convert;
import com.yuxuan66.ecmc.modules.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/14
 */
@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {


    private final UserService userService;
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return userService.getPermCode(Convert.toLong(loginId));
    }

    /**
     * 获得当前用户的权限ID列表
     * TODO 由于系统中没有判断角色权限的地方，当前暂不实现
     * @param loginId  账号id
     * @param loginType 账号类型
     * @return 角色ID列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return new ArrayList<>();
    }
}
