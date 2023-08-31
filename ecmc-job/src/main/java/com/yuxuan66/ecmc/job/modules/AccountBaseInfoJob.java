package com.yuxuan66.ecmc.job.modules;

import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.UserAccountMapper;
import com.yuxuan66.ecmc.modules.account.service.UserAccountService;
import com.yuxuan66.ecmc.modules.account.service.refresh.AccountWalletRefresh;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
@Component
@Transactional
@RequiredArgsConstructor
public class AccountBaseInfoJob {

    @Resource
    private UserAccountMapper userAccountMapper;
    private final UserAccountService userAccountService;
    private final AccountWalletRefresh accountWalletRefresh;

    @Scheduled(cron = "0 0 * ? * *")
    public void process() throws Exception {
        List<UserAccount> userAccountList = userAccountMapper.selectList(null);
        for (UserAccount userAccount : userAccountList) {
            userAccountService.refresh(userAccount);
            accountWalletRefresh.refresh(userAccount);
        }
    }
}
