package com.yuxuan66.ecmc.modules.account.service.refresh;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.common.utils.Lang;
import com.yuxuan66.ecmc.modules.account.entity.AccountSkillQueue;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.AccountSkillQueueMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.troja.eve.esi.ApiException;
import net.troja.eve.esi.api.SkillsApi;
import net.troja.eve.esi.model.CharacterSkillqueueResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 刷新用户技能队列
 *
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountSkillQueueRefresh {

    @Resource
    private AccountSkillQueueMapper accountSkillQueueMapper;

    @Async("threadPoolTaskExecutor")
    public void refresh(UserAccount userAccount, Map<Integer, Type> typeMap) {
        userAccount.esiClient();
        SkillsApi skillsApi = new SkillsApi();
        try {
            List<CharacterSkillqueueResponse> skillQueue = skillsApi.getCharactersCharacterIdSkillqueue(userAccount.getCharacterId(), "", "", userAccount.getAccessToken());
            List<AccountSkillQueue> queueList = new ArrayList<>();
            for (CharacterSkillqueueResponse skill : skillQueue) {
                AccountSkillQueue queue = new AccountSkillQueue();
                queue.setUserId(userAccount.getUserId());
                queue.setAccountId(userAccount.getId());
                queue.setCharacterId(userAccount.getCharacterId());
                queue.setCharacterName(userAccount.getCharacterName());
                queue.setSkillId(skill.getSkillId());
                queue.setSkillName(typeMap.containsKey(skill.getSkillId()) ? typeMap.get(skill.getSkillId()).getName() : "未知技能,ID: " + skill.getSkillId());
                queue.setFinishDate(Lang.get(skill.getFinishDate()));
                queue.setFinishedLevel(skill.getFinishedLevel());
                queue.setLevelEndSp(skill.getLevelEndSp());
                queue.setLevelStartSp(skill.getLevelStartSp());
                queue.setQueuePosition(skill.getQueuePosition());
                queue.setStartDate(Lang.get(skill.getStartDate()));
                queue.setTrainingStartSp(skill.getTrainingStartSp());
                queueList.add(queue);
            }

            accountSkillQueueMapper.delete(new QueryWrapper<AccountSkillQueue>().eq("account_id", userAccount.getId()));
            if (!queueList.isEmpty()) {
                accountSkillQueueMapper.batchInsert(queueList);
            }
        } catch (ApiException e) {
            log.error(e.getResponseBody(), e);
        }
    }
}
