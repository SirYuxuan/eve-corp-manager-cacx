package com.yuxuan66.ecmc.modules.account.service.refresh;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.common.utils.Lang;
import com.yuxuan66.ecmc.modules.account.entity.AccountContract;
import com.yuxuan66.ecmc.modules.account.entity.AccountContractItem;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.AccountContractItemMapper;
import com.yuxuan66.ecmc.modules.account.mapper.AccountContractMapper;
import com.yuxuan66.ecmc.support.base.BaseRefresh;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.troja.eve.esi.ApiException;
import net.troja.eve.esi.ApiResponse;
import net.troja.eve.esi.api.ContractsApi;
import net.troja.eve.esi.model.CharacterContractsItemsResponse;
import net.troja.eve.esi.model.CharacterContractsResponse;
import net.troja.eve.esi.model.UniverseNamesResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 刷新用户合同
 *
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountContractRefresh extends BaseRefresh {

    @Resource
    private AccountContractMapper accountContractMapper;
    @Resource
    private AccountContractItemMapper accountContractItemMapper;
    private final EveCache eveCache;


    public List<CharacterContractsResponse> getList(UserAccount userAccount, int page) throws ApiException {
        ContractsApi contractsApi = new ContractsApi();
        List<CharacterContractsResponse> result = new ArrayList<>();
        ApiResponse<List<CharacterContractsResponse>> info = contractsApi.getCharactersCharacterIdContractsWithHttpInfo(userAccount.getCharacterId(), null, null, page, userAccount.getAccessToken());
        if (!info.getData().isEmpty()) {
            result.addAll(info.getData());
        }
        List<String> list = info.getHeaders().get("x-pages");
        if (list.size() == 1 && Convert.toInt(list.get(0), 1) > page) {
            result.addAll(getList(userAccount, page + 1));
        }

        return result;
    }

    @Async("threadPoolTaskExecutor")
    public void refresh(UserAccount userAccount, Map<Integer, Type> typeMap) {
        userAccount.esiClient();
        ContractsApi contractsApi = new ContractsApi();
        contractsApi.getApiClient().setConnectTimeout(Integer.MAX_VALUE);
        try {
            // 1. 查出此人当前的合同。如果是不存在的则新增，如果已存在的状态变动则更新
            List<AccountContract> nowContractList = accountContractMapper.selectList(new QueryWrapper<AccountContract>().eq("account_id", userAccount.getId()));
            // 用户所有合同列表
            List<CharacterContractsResponse> contracts = getList(userAccount, 1);
            // 新合同列表
            List<CharacterContractsResponse> newContractList = new ArrayList<>();
            // 需要更新的合同列表
            List<CharacterContractsResponse> oldContractList = new ArrayList<>();
            // 处理接受人和受让人ID
            List<Integer> acceptorList = new ArrayList<>();
            List<Integer> assigneeList = new ArrayList<>();

            // 2. 过滤出已存在的
            for (CharacterContractsResponse contract : contracts) {
                // 添加接受人和受让人到ID列表 用来获取名称
                if (contract.getAcceptorId() != null && !acceptorList.contains(contract.getAcceptorId())) {
                    acceptorList.add(contract.getAcceptorId());
                }
                if (contract.getAssigneeId() != null && !assigneeList.contains(contract.getAssigneeId())) {
                    assigneeList.add(contract.getAssigneeId());
                }

                Optional<AccountContract> contractOptional = nowContractList.stream().filter(item -> item.getContractId().equals(contract.getContractId())).findAny();
                if (contractOptional.isPresent()) {
                    // 合同存在, 需要判断状态是否需要更新
                    AccountContract accountContract = contractOptional.get();
                    if (!accountContract.getStatus().equals(contract.getStatusString())) {
                        oldContractList.add(contract);
                    }
                } else {
                    // 合同不存在, 需要拉取合同详细信息报错
                    newContractList.add(contract);
                }
            }
            // 处理接受人和受让人的名称
            List<UniverseNamesResponse> acceptorNames = universeNames(acceptorList);
            List<UniverseNamesResponse> assigneeNames = universeNames(assigneeList);
            Map<Integer, UniverseNamesResponse> acceptorNameMap = new HashMap<>();
            Map<Integer, UniverseNamesResponse> assigneeNameMap = new HashMap<>();
            acceptorNames.forEach(item -> acceptorNameMap.put(item.getId(), item));
            assigneeNames.forEach(item -> assigneeNameMap.put(item.getId(), item));


            // 处理需要更新的合同
            for (CharacterContractsResponse contract : oldContractList) {
                Optional<AccountContract> contractOptional = nowContractList.stream().filter(item -> item.getContractId().equals(contract.getContractId())).findAny();
                AccountContract accountContract = contractOptional.orElseGet(AccountContract::new);
                accountContract.setStatus(contract.getStatusString());
                setContractBaseInfo(acceptorNameMap, assigneeNameMap, contract, accountContract);
                accountContract.updateById();
            }

            // 处理新合同数据
            List<AccountContract> accountContractList = new ArrayList<>();
            List<AccountContractItem> accountContractItemList = new ArrayList<>();
            for (CharacterContractsResponse contract : newContractList) {
                userAccount.esiClient();
               log.info("正在处理{}的合同, 已处理:{},剩余:{}", userAccount.getCharacterName(), accountContractList.size(), newContractList.size() - accountContractList.size());
                AccountContract accountContract = new AccountContract();
                BeanUtil.copyProperties(contract, accountContract);
                accountContract.setContractId(contract.getContractId());
                accountContract.setUserId(userAccount.getUserId());
                accountContract.setAccountId(userAccount.getId());
                accountContract.setCharacterId(userAccount.getCharacterId());
                accountContract.setCharacterName(userAccount.getCharacterName());
                setContractBaseInfo(acceptorNameMap, assigneeNameMap, contract, accountContract);
                if (accountContract.getStartLocationId() != null) {
                    if (accountContract.getStartLocationId() > Integer.MAX_VALUE) {
                        accountContract.setStartLocationName(eveCache.getStructures(accountContract.getStartLocationId(), userAccount.getCharacterId(), userAccount.getAccessToken()));
                    } else {
                        accountContract.setStartLocationName(eveCache.getStations(Convert.toInt(accountContract.getStartLocationId())).getName());
                    }
                }
                if (accountContract.getEndLocationId() != null) {
                    if (accountContract.getEndLocationId() > Integer.MAX_VALUE) {
                        accountContract.setEndLocationName(eveCache.getStructures(accountContract.getEndLocationId(), userAccount.getCharacterId(), userAccount.getAccessToken()));
                    } else {
                        accountContract.setEndLocationName(eveCache.getStations(Convert.toInt(accountContract.getEndLocationId())).getName());
                    }
                }
                accountContract.setIssuerCorporationName(eveCache.getCorporationInfo(accountContract.getIssuerCorporationId()).getName());
                accountContract.setIssuerName(eveCache.getCharacterInfo(accountContract.getIssuerId()).getName());

                accountContractList.add(accountContract);


                List<CharacterContractsItemsResponse> items = contract.getType() == CharacterContractsResponse.TypeEnum.COURIER ? new ArrayList<>() : contractsApi.getCharactersCharacterIdContractsContractIdItems(userAccount.getCharacterId(), contract.getContractId(), null, null, userAccount.getAccessToken());

                for (CharacterContractsItemsResponse item : items) {
                    AccountContractItem accountContractItem = new AccountContractItem();
                    BeanUtil.copyProperties(item, accountContractItem);
                    accountContractItem.setAccountId(userAccount.getId());
                    accountContractItem.setContractId(contract.getContractId());
                    accountContractItem.setTypeName(typeName(typeMap, accountContractItem.getTypeId()));
                    // accountContractItem.setSellPrice((double) eveCache.getMinSellPrice(accountContractItem.getTypeId()));
                    // accountContractItem.setBuyPrice((double) eveCache.getMaxBuyPrice(accountContractItem.getTypeId()));
                    accountContractItemList.add(accountContractItem);
                }
            }
            if (!accountContractList.isEmpty()) {
                accountContractMapper.batchInsert(accountContractList);
            }
            if (!accountContractItemList.isEmpty()) {
                accountContractItemMapper.batchInsert(accountContractItemList);
            }

            log.info("用户{}合同处理完成", userAccount.getCharacterName());
        } catch (ApiException e) {
            log.error(e.getResponseBody(), e);
        }
    }

    /**
     * 给合同设置基本数据
     *
     * @param acceptorNameMap 接受人名称Map
     * @param assigneeNameMap 受让人名称Map
     * @param contract        合同数据
     * @param accountContract 用户合同
     */
    private void setContractBaseInfo(Map<Integer, UniverseNamesResponse> acceptorNameMap, Map<Integer, UniverseNamesResponse> assigneeNameMap, CharacterContractsResponse contract, AccountContract accountContract) {
        accountContract.setAcceptorId(contract.getAcceptorId());
        if (acceptorNameMap.containsKey(accountContract.getAcceptorId())) {
            UniverseNamesResponse response = acceptorNameMap.get(accountContract.getAcceptorId());
            accountContract.setAcceptorName(response.getName());
            accountContract.setAcceptorType(response.getCategoryString());
        }

        accountContract.setAssigneeId(contract.getAssigneeId());
        if (assigneeNameMap.containsKey(accountContract.getAssigneeId())) {
            UniverseNamesResponse response = assigneeNameMap.get(accountContract.getAssigneeId());
            accountContract.setAssigneeName(response.getName());
            accountContract.setAssigneeType(response.getCategoryString());
        }

        accountContract.setDateExpired(Lang.get(contract.getDateExpired()));
        accountContract.setDateCompleted(Lang.get(contract.getDateCompleted()));
        accountContract.setDateAccepted(Lang.get(contract.getDateAccepted()));
        accountContract.setDateIssued(Lang.get(contract.getDateIssued()));
    }
}
