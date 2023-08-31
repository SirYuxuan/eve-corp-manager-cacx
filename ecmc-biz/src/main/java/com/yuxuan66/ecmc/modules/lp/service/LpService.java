package com.yuxuan66.ecmc.modules.lp.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.common.consts.Const;
import com.yuxuan66.ecmc.common.utils.StpUtil;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.UserAccountMapper;
import com.yuxuan66.ecmc.modules.lp.entity.LpLog;
import com.yuxuan66.ecmc.modules.lp.entity.consts.LpSource;
import com.yuxuan66.ecmc.modules.lp.entity.consts.LpType;
import com.yuxuan66.ecmc.modules.lp.entity.dto.SendLpDto;
import com.yuxuan66.ecmc.modules.lp.entity.query.LpLogQuery;
import com.yuxuan66.ecmc.modules.lp.mapper.LpLogMapper;
import com.yuxuan66.ecmc.modules.system.service.UserService;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LpService extends BaseService<LpLog, LpLogMapper> {

    private final UserService userService;
    @Resource
    private UserAccountMapper userAccountMapper;

    /**
     * 给指定用户发放LP
     *
     * @param sendLpDto 发放信息
     */
    public void sendLp(SendLpDto sendLpDto) {

        String[] userList = sendLpDto.getLpUsers().split("\n");

        // 处理正确的角色名称
        List<String> sendUserList = new ArrayList<>();

        for (String user : userList) {
            // 移除行尾换行，移除空格
            String tempUser = user.replace("\r", "").trim();
            if (StrUtil.isNotBlank(tempUser)) {
                sendUserList.add(tempUser);
            }
        }

        // 拉取系统中所有角色名单，不在系统中的直接忽略
        QueryWrapper<UserAccount> wrapper = new QueryWrapper<UserAccount>().in("character_name", sendUserList);
        wrapper.eq(sendLpDto.isCorp(), "corp_id", ConfigKit.get(CacheKey.EVE_MAIN_CORP));
        List<UserAccount> userAccountList = userAccountMapper.selectList(wrapper);

        if (userAccountList.isEmpty()) {
            throw new BizException("LP发放失败,您复制的名单在系统中均为注册");
        }

        List<LpLog> saveLogList = new ArrayList<>();

        for (UserAccount userAccount : userAccountList) {
            LpLog log = new LpLog();
            log.setCharacterName(userAccount.getCharacterName());
            log.setAccountId(userAccount.getId());
            log.setUserId(userAccount.getUserId());
            log.setLp(sendLpDto.getLp());
            log.setSource(LpSource.MANUAL_RELEASE);
            log.setType(LpType.INCOME);
            log.setContent(sendLpDto.getWhere());
            saveLogList.add(log);
            // 更新用户的LP
            userAccount.setLpNow(userAccount.getLpNow().add(sendLpDto.getLp()));
            userAccount.setLpTotal(userAccount.getLpTotal().add(sendLpDto.getLp()));
            userAccount.updateById();

        }
        baseMapper.batchInsert(saveLogList);

    }

    /**
     * 查询指定角色或用户的LP历史
     *
     * @param lpLogQuery 查询条件
     * @return LP历史
     */
    public Page<LpLog> listLpLogByAccountId(LpLogQuery lpLogQuery) {
        return query().eq(lpLogQuery.getAccountId() != null, "account_id", lpLogQuery.getAccountId())
                .eq(lpLogQuery.getAccountId() == null, "user_id", StpUtil.getLoginId()).page(lpLogQuery.getPageOrder());
    }

    /**
     * 分页查询LP发放记录，如果有发放的权限则查看所有，否则查看自己的
     *
     * @param lpLogQuery 查询条件
     * @return LP历史
     */
    public Page<LpLog> listLpLog(LpLogQuery lpLogQuery) {
        if (!userService.getPermCode(StpUtil.getLoginId()).contains(Const.LP_SEND)) {
            lpLogQuery.setUserId(StpUtil.getLoginId());
        }
        return baseMapper.selectLpLog(lpLogQuery.getPage(), lpLogQuery);
    }


}
