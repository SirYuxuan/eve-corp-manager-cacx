package com.yuxuan66.ecmc.modules.account.service.refresh;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.PageUtil;
import com.alibaba.fastjson.JSONArray;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.common.utils.Lang;
import com.yuxuan66.ecmc.modules.account.entity.AccountOrder;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.AccountOrderMapper;
import com.yuxuan66.ecmc.support.base.BaseRefresh;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.troja.eve.esi.ApiException;
import net.troja.eve.esi.ApiResponse;
import net.troja.eve.esi.api.UniverseApi;
import net.troja.eve.esi.api.WalletApi;
import net.troja.eve.esi.model.CharacterOrdersHistoryResponse;
import net.troja.eve.esi.model.CharacterWalletTransactionsResponse;
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
public class AccountMarketRefresh extends BaseRefresh {

    @Resource
    private AccountOrderMapper accountOrderMapper;
    private final EveCache eveCache;



    @Async("threadPoolTaskExecutor")
    public void refresh(UserAccount userAccount, Map<Integer, Type> typeMap) {
        userAccount.esiClient();
        try {
            List<AccountOrder> accountOrders = new ArrayList<>();
            AccountOrder lastId = accountOrderMapper.getLastId(userAccount.getId());
            List<CharacterWalletTransactionsResponse> transactions = new WalletApi().getCharactersCharacterIdWalletTransactions(userAccount.getCharacterId(), null, null,null, userAccount.getAccessToken()).stream().filter(item->item.getTransactionId() > (lastId == null ? 0 : lastId.getOrderId())).toList();
            List<Integer> cliendIdList = transactions.stream().map(CharacterWalletTransactionsResponse::getClientId).toList();
            List<UniverseNamesResponse> clientName = universeNames(cliendIdList);

            Map<Integer, UniverseNamesResponse> clientNameMap = new HashMap<>(clientName.size());
            clientName.forEach(item -> clientNameMap.put(item.getId(), item));
            for (CharacterWalletTransactionsResponse order : transactions) {
                AccountOrder accountOrder = new AccountOrder();
                BeanUtil.copyProperties(order, accountOrder);
                accountOrder.setOrderId(order.getTransactionId());
                accountOrder.setUserId(userAccount.getUserId());
                accountOrder.setAccountId(userAccount.getId());
                accountOrder.setCharacterId(userAccount.getCharacterId());
                accountOrder.setCharacterName(userAccount.getCharacterName());
                accountOrder.setDate(Lang.get(order.getDate()));
                if(clientNameMap.containsKey(order.getClientId())){
                    UniverseNamesResponse response = clientNameMap.get(accountOrder.getClientId());
                    accountOrder.setClientName(response.getName());
                    accountOrder.setClientType(response.getCategoryString());
                }

                if (typeMap.containsKey(accountOrder.getTypeId())) {
                    accountOrder.setTypeName(typeMap.get(accountOrder.getTypeId()).getName());
                } else {
                    accountOrder.setTypeName(eveCache.typeName(accountOrder.getTypeId()).getName());
                }
                if (accountOrder.getLocationId() > Integer.MAX_VALUE) {
                    try {
                        accountOrder.setLocationName(eveCache.getStructures(accountOrder.getLocationId(), userAccount.getCharacterId(),userAccount.getAccessToken()));
                    } catch (Exception e) {
                        accountOrder.setLocationName("无法读取建筑名称");
                    }
                } else {
                    accountOrder.setLocationName(eveCache.getStations(Convert.toInt(accountOrder.getLocationId())).getName());
                }
                accountOrders.add(accountOrder);

            }
            if (!accountOrders.isEmpty()) {
                accountOrderMapper.batchInsert(accountOrders);
            }


        } catch (ApiException e) {
            log.error(e.getResponseBody(), e);
        }
    }
}
