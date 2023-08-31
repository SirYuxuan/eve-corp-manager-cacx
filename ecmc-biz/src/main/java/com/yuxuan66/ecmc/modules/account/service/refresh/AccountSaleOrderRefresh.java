package com.yuxuan66.ecmc.modules.account.service.refresh;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.modules.account.entity.AccountOrder;
import com.yuxuan66.ecmc.modules.account.entity.AccountSaleOrder;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.AccountOrderMapper;
import com.yuxuan66.ecmc.modules.account.mapper.AccountSaleOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.troja.eve.esi.ApiException;
import net.troja.eve.esi.api.MarketApi;
import net.troja.eve.esi.api.UniverseApi;
import net.troja.eve.esi.api.WalletApi;
import net.troja.eve.esi.model.CharacterOrdersResponse;
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
public class AccountSaleOrderRefresh {

    @Resource
    private AccountSaleOrderMapper accountSaleOrderMapper;
    private final EveCache eveCache;



    @Async("threadPoolTaskExecutor")
    public void refresh(UserAccount userAccount, Map<Integer, Type> typeMap) {
        userAccount.esiClient();
        try {
            List<AccountSaleOrder> accountOrders = new ArrayList<>();
            List<CharacterOrdersResponse> ordersResponses = new MarketApi().getCharactersCharacterIdOrders(userAccount.getCharacterId(), null, null, userAccount.getAccessToken());

            for (CharacterOrdersResponse order : ordersResponses) {
                AccountSaleOrder accountOrder = new AccountSaleOrder();
                BeanUtil.copyProperties(order, accountOrder);
                accountOrder.setUserId(userAccount.getUserId());
                accountOrder.setAccountId(userAccount.getId());
                accountOrder.setCharacterId(userAccount.getCharacterId());
                accountOrder.setCharacterName(userAccount.getCharacterName());

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
                accountOrder.setRegionName(eveCache.getRegion(accountOrder.getRegionId()).getName());
                accountOrders.add(accountOrder);

            }
            accountSaleOrderMapper.delete(new QueryWrapper<AccountSaleOrder>().eq("account_id",userAccount.getId()));
            if (!accountOrders.isEmpty()) {
                accountSaleOrderMapper.batchInsert(accountOrders);
            }


        } catch (ApiException e) {
            log.error(e.getResponseBody(), e);
        }
    }
}
