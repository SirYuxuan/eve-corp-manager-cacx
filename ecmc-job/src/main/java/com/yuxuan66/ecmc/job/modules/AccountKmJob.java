package com.yuxuan66.ecmc.job.modules;

import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.modules.account.entity.AccountKillMail;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.AccountKillMailMapper;
import com.yuxuan66.ecmc.modules.account.mapper.UserAccountMapper;
import com.yuxuan66.ecmc.modules.account.service.refresh.AccountKillMailRefresh;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Sir丶雨轩
 * @since 2022/12/22
 */
@Component
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class AccountKmJob  {

    private final EveCache eveCache;

    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private AccountKillMailMapper accountKillMailMapper;
    private final AccountKillMailRefresh accountKillMailRefresh;

    @Scheduled(cron = "0 25 * ? * *")
    public void process( ) throws Exception {
        List<UserAccount> userAccountList = userAccountMapper.selectList(null);
        Map<Integer, Type> typeMap = eveCache.getTypeMap();
        List<AccountKillMail> killMailList = accountKillMailMapper.selectList(null);
        for (UserAccount userAccount : userAccountList) {
            accountKillMailRefresh.refresh(userAccount, typeMap, killMailList);
        }

    }
}
