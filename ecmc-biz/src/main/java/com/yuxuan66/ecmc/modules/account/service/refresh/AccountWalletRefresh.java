package com.yuxuan66.ecmc.modules.account.service.refresh;

import com.yuxuan66.ecmc.common.utils.Lang;
import com.yuxuan66.ecmc.modules.account.entity.AccountWallet;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.AccountWalletMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.troja.eve.esi.ApiException;
import net.troja.eve.esi.api.UniverseApi;
import net.troja.eve.esi.api.WalletApi;
import net.troja.eve.esi.model.CharacterWalletJournalResponse;
import net.troja.eve.esi.model.UniverseNamesResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 刷新用户技能队列
 *
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountWalletRefresh {

    @Resource
    private AccountWalletMapper accountWalletMapper;

    @Async("threadPoolTaskExecutor")
    public void refresh(UserAccount userAccount) {
        userAccount.esiClient();
        WalletApi walletApi = new WalletApi();
        try {
            List<CharacterWalletJournalResponse> walletJournal = walletApi.getCharactersCharacterIdWalletJournal(userAccount.getCharacterId(), "", "", 1,null);
            List<AccountWallet> accountWalletList = new ArrayList<>();
            AccountWallet lastId = accountWalletMapper.getLastId(userAccount.getId());
            List<CharacterWalletJournalResponse> saveList = walletJournal.stream().filter(item -> item.getId() > (lastId == null ? 0 : lastId.getJournalId())).toList();
            if (saveList.isEmpty()) {
                return;
            }
            // 处理交易用户的名称和类型
            List<Integer> secondPartIdList = saveList.stream().map(CharacterWalletJournalResponse::getSecondPartyId).toList();
            List<Integer> firstPartyList = saveList.stream().map(CharacterWalletJournalResponse::getFirstPartyId).toList();
            UniverseApi universeApi = new UniverseApi();
            List<UniverseNamesResponse> secondPartName = secondPartIdList.isEmpty() ? new ArrayList<>() : universeApi.postUniverseNames(new HashSet<>(secondPartIdList).stream().toList(), "");
            List<UniverseNamesResponse> firstPartyName = firstPartyList.isEmpty() ? new ArrayList<>() : universeApi.postUniverseNames(new HashSet<>(firstPartyList).stream().toList(), "");
            Map<Integer, UniverseNamesResponse> secondPartNameMap = new HashMap<>(secondPartName.size());
            Map<Integer, UniverseNamesResponse> firstPartyNameMap = new HashMap<>(firstPartyName.size());
            secondPartName.forEach(item -> secondPartNameMap.put(item.getId(), item));
            firstPartyName.forEach(item -> firstPartyNameMap.put(item.getId(), item));


            for (CharacterWalletJournalResponse journal : saveList) {
                AccountWallet accountWallet = new AccountWallet();
                accountWallet.setJournalId(journal.getId());
                accountWallet.setUserId(userAccount.getUserId());
                accountWallet.setAccountId(userAccount.getId());
                accountWallet.setCharacterId(userAccount.getCharacterId());
                accountWallet.setCharacterName(userAccount.getCharacterName());
                accountWallet.setAmount(journal.getAmount());
                accountWallet.setBalance(journal.getBalance());
                accountWallet.setContextId(journal.getContextId());
                accountWallet.setContextIdType(journal.getContextIdTypeString());
                accountWallet.setDate(Lang.get(journal.getDate()));
                accountWallet.setFirstPartyId(journal.getFirstPartyId());
                if (firstPartyNameMap.containsKey(accountWallet.getFirstPartyId())) {
                    UniverseNamesResponse namesResponse = firstPartyNameMap.get(accountWallet.getFirstPartyId());
                    accountWallet.setFirstPartyName(namesResponse.getName());
                    accountWallet.setFirstPartyType(namesResponse.getCategoryString());
                }
                accountWallet.setDescription(journal.getDescription());
                accountWallet.setReason(journal.getReason());
                accountWallet.setRefType(journal.getRefTypeString());
                accountWallet.setSecondPartyId(journal.getSecondPartyId());
                if (secondPartNameMap.containsKey(accountWallet.getSecondPartyId())) {
                    UniverseNamesResponse namesResponse = secondPartNameMap.get(accountWallet.getSecondPartyId());
                    accountWallet.setSecondPartyName(namesResponse.getName());
                    accountWallet.setSecondPartyType(namesResponse.getCategoryString());
                }

                accountWallet.setTax(journal.getTax());
                accountWallet.setTaxReceiverId(journal.getTaxReceiverId());
                accountWalletList.add(accountWallet);
            }
            accountWalletMapper.batchInsert(accountWalletList);
        } catch (ApiException e) {
            log.error(e.getResponseBody(), e);
        }
    }
}
