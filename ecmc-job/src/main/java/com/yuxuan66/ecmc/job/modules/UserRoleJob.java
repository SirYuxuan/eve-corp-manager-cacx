package com.yuxuan66.ecmc.job.modules;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.common.consts.Const;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.UserAccountMapper;
import com.yuxuan66.ecmc.modules.system.entity.User;
import com.yuxuan66.ecmc.modules.system.entity.UsersRoles;
import com.yuxuan66.ecmc.modules.system.mapper.UserMapper;
import com.yuxuan66.ecmc.modules.system.mapper.UsersRolesMapper;
import lombok.RequiredArgsConstructor;
import net.troja.eve.esi.api.CorporationApi;
import net.troja.eve.esi.model.CorporationMembersTitlesResponse;
import net.troja.eve.esi.model.CorporationRolesResponse;
import net.troja.eve.esi.model.CorporationTitlesResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sir丶雨轩
 * @since 2022/12/24
 */
@Component
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserRoleJob  {
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private UsersRolesMapper usersRolesMapper;
    @Resource
    private UserMapper userMapper;

    @Scheduled(cron = "0 50 * ? * *")
    public void process() throws Exception {
        UserAccount userAccount = userAccountMapper.selectById(1);
        userAccount.esiClient();
        CorporationApi corporationApi = new CorporationApi();

        List<Integer> corporationIdMembers = corporationApi.getCorporationsCorporationIdMembers(userAccount.getCorpId(), "", "", userAccount.getAccessToken());
        List<CorporationMembersTitlesResponse> membersTitles = corporationApi.getCorporationsCorporationIdMembersTitles(userAccount.getCorpId(), "", "", userAccount.getAccessToken());
        List<CorporationTitlesResponse> titles = corporationApi.getCorporationsCorporationIdTitles(userAccount.getCorpId(), "", "", userAccount.getAccessToken());
        List<CorporationRolesResponse> corporationIdRoles = corporationApi.getCorporationsCorporationIdRoles(userAccount.getCorpId(), "", "", userAccount.getAccessToken());
        // 头衔ID=>头衔名称
        Map<Integer, String> titleMap = new HashMap<>(titles.size());
        for (CorporationTitlesResponse title : titles) {
            titleMap.put(title.getTitleId(), title.getName());
        }
        // 角色ID=>头衔列表
        Map<Integer, List<Integer>> memberTitleMap = new HashMap<>(membersTitles.size());
        for (CorporationMembersTitlesResponse title : membersTitles) {
            memberTitleMap.put(title.getCharacterId(), title.getTitles());
        }
        // 角色ID=>是否是总监
        List<Integer> directorList = new ArrayList<>();
        for (CorporationRolesResponse role : corporationIdRoles) {
            if (role.getRoles() != null && role.getRoles().stream().anyMatch(item -> item == CorporationRolesResponse.RolesEnum.DIRECTOR)) {
                directorList.add(role.getCharacterId());
            }
        }

        // 查出所有用户和所有角色
        List<UserAccount> userAccounts = userAccountMapper.selectList(null);
        List<User> userList = userMapper.selectList(null);
        List<UsersRoles> usersRolesList = usersRolesMapper.selectList(null);
        // 处理用户的权限
        for (User user : userList) {
            // 查找出这个用户的所有角色
            List<UserAccount> meAccount = userAccounts.stream().filter(item -> item.getUserId().equals(user.getId())).toList();
            // 查找出这个角色所有的头衔
            List<Integer> titleIds = new ArrayList<>();
            for (UserAccount account : meAccount) {
                if (memberTitleMap.containsKey(account.getCharacterId())) {
                    titleIds.addAll(memberTitleMap.get(account.getCharacterId()));
                }
            }
            // LP管理员/商城管理员/补损官
            boolean lpAdmin = titleIds.stream().anyMatch(item -> "LP管理员".equalsIgnoreCase(titleMap.get(item)));
            boolean shopAdmin = titleIds.stream().anyMatch(item -> "商城管理员".equalsIgnoreCase(titleMap.get(item)));
            boolean srpAdmin = titleIds.stream().anyMatch(item -> "补损官".equalsIgnoreCase(titleMap.get(item)));
            boolean isDirector = meAccount.stream().anyMatch(item->directorList.contains(item.getCharacterId()));

            // 查询这个用户拥有的角色
            List<Long> userRoleIds = usersRolesList.stream().filter(item -> item.getUserId().equals(user.getId())).map(UsersRoles::getRoleId).toList();

            if(isDirector){
                // 如果此人是总监，添加上所有权限后跳出本次循环
                if(!userRoleIds.contains(Const.DIRECTOR_ROLE)){
                    new UsersRoles(user.getId(), Const.DIRECTOR_ROLE).insert();
                }
                continue;
            }

            // 如果没有LP管理员的头衔且拥有此角色则删除
            if (!lpAdmin && userRoleIds.contains(Const.LP_ADMIN_ROLE)) {
                usersRolesMapper.delete(new QueryWrapper<UsersRoles>().eq("user_id", user.getId()).eq("role_id", Const.LP_ADMIN_ROLE));
            }
            // 如果是LP管理员且没有此角色则添加
            if (lpAdmin && !userRoleIds.contains(Const.LP_ADMIN_ROLE)) {
                new UsersRoles(user.getId(), Const.LP_ADMIN_ROLE).insert();
            }

            // 如果没有商城管理员的头衔且拥有此角色则删除
            if (!shopAdmin && userRoleIds.contains(Const.SHOP_ADMIN_ROLE)) {
                usersRolesMapper.delete(new QueryWrapper<UsersRoles>().eq("user_id", user.getId()).eq("role_id", Const.SHOP_ADMIN_ROLE));
            }
            // 如果是商城管理员且没有此角色则添加
            if (shopAdmin && !userRoleIds.contains(Const.SHOP_ADMIN_ROLE)) {
                new UsersRoles(user.getId(), Const.SHOP_ADMIN_ROLE).insert();
            }

            // 如果没有补损官的头衔且拥有此角色则删除
            if (!srpAdmin && userRoleIds.contains(Const.SRP_ADMIN_ROLE)) {
                usersRolesMapper.delete(new QueryWrapper<UsersRoles>().eq("user_id", user.getId()).eq("role_id", Const.SRP_ADMIN_ROLE));
            }
            // 如果是补损官且没有此角色则添加
            if (lpAdmin && !userRoleIds.contains(Const.SRP_ADMIN_ROLE)) {
                new UsersRoles(user.getId(), Const.SRP_ADMIN_ROLE).insert();
            }
            // 判断此人的角色是否都在军团内
            // boolean isCorp = meAccount.stream().anyMatch(item -> ConfigKit.get(CacheKey.EVE_MAIN_CORP, Integer.class).equals(item.getCorpId()));
            boolean isCorp = meAccount.stream().anyMatch(item -> corporationIdMembers.contains(item.getCharacterId()));

            // 如果没有角色在军团，且拥有军团权限
            if (!isCorp && userRoleIds.contains(Const.CORP_ROLE)) {
                usersRolesMapper.delete(new QueryWrapper<UsersRoles>().eq("user_id", user.getId()).eq("role_id", Const.CORP_ROLE));
            }
            // 如果是军团成员且没有此角色则添加
            if (isCorp && !userRoleIds.contains(Const.CORP_ROLE)) {
                new UsersRoles(user.getId(), Const.CORP_ROLE).insert();
            }

        }


    }
}
