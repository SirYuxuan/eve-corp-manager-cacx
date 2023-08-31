package com.yuxuan66.ecmc.modules.account.service.refresh;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.common.utils.Lang;
import com.yuxuan66.ecmc.modules.account.entity.AccountKillMail;
import com.yuxuan66.ecmc.modules.account.entity.AccountKillMailItem;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.AccountKillMailItemMapper;
import com.yuxuan66.ecmc.modules.account.mapper.AccountKillMailMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.troja.eve.esi.ApiException;
import net.troja.eve.esi.api.KillmailsApi;
import net.troja.eve.esi.model.CharacterKillmailsResponse;
import net.troja.eve.esi.model.KillmailItem;
import net.troja.eve.esi.model.KillmailResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 刷新用户KM记录
 * @author Sir丶雨轩
 * @since 2022/12/23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountKillMailRefresh {

    private final EveCache eveCache;
    @Resource
    private AccountKillMailMapper accountKillMailMapper;
    @Resource
    private AccountKillMailItemMapper accountKillMailItemMapper;

    @Async("threadPoolTaskExecutor")
    public void refresh(UserAccount userAccount, Map<Integer,Type> typeMap,List<AccountKillMail> killMailList) {
        userAccount.esiClient();
        KillmailsApi kmApi = new KillmailsApi();
       try{
           List<CharacterKillmailsResponse> killmailsRecent = kmApi.getCharactersCharacterIdKillmailsRecent(userAccount.getCharacterId(), "", null, null, userAccount.getAccessToken());
           for (CharacterKillmailsResponse killmailsResponse : killmailsRecent) {

               // 判断此KM是否存在
               if (killMailList.stream().anyMatch(item->item.getKillMailId().equals(killmailsResponse.getKillmailId()) && item.getKillMailHash().equals(killmailsResponse.getKillmailHash()))) {
                   continue;
               }

               KillmailResponse killmailData = kmApi.getKillmailsKillmailIdKillmailHash(killmailsResponse.getKillmailHash(), killmailsResponse.getKillmailId(), "", "");
               // 如果此KM的损失角色ID不是自身，说明是击毁别人的记录
               if(!userAccount.getCharacterId().equals(killmailData.getVictim().getCharacterId())){
                   continue;
               }
               AccountKillMail accountKillMail = new AccountKillMail();
               accountKillMail.setKillMailId(killmailData.getKillmailId());
               accountKillMail.setKillMailHash(killmailsResponse.getKillmailHash());
               accountKillMail.setKillMailTime(Lang.get(killmailData.getKillmailTime()));
               accountKillMail.setIsNpc(killmailData.getAttackers().stream().noneMatch(item -> item.getCharacterId() != null));
               accountKillMail.setSolarSystemId(killmailData.getSolarSystemId());
               accountKillMail.setSolarSystemName(eveCache.getSolarSystemName(accountKillMail.getSolarSystemId()).getName());
               accountKillMail.setShipTypeId(killmailData.getVictim().getShipTypeId());
               accountKillMail.setShipTypeName(eveCache.getTypeMap().getOrDefault(killmailData.getVictim().getShipTypeId(),new Type()).getName());
               accountKillMail.setAccountId(userAccount.getId());
               accountKillMail.setUserId(userAccount.getUserId());

               accountKillMail.setCharacterId(killmailData.getVictim().getCharacterId());
               accountKillMail.setCharacterName(eveCache.getCharacterInfo(accountKillMail.getCharacterId()).getName());
               accountKillMail.setCorporationId(killmailData.getVictim().getCorporationId());
               accountKillMail.setCorporationName(eveCache.getCorporationInfo(accountKillMail.getCorporationId()).getName());
               accountKillMail.setAllianceId(killmailData.getVictim().getAllianceId());
               if(accountKillMail.getAllianceId() != null){
                   accountKillMail.setAllianceName(eveCache.getAllianceInfo(accountKillMail.getAllianceId()).getName());
               }


               accountKillMail.insert();
               List<AccountKillMailItem> accountKillMailItems = new ArrayList<>();
               BigDecimal itemPrice = BigDecimal.ZERO;
               for (KillmailItem item : Objects.requireNonNull(killmailData.getVictim().getItems())) {
                   // TODO 损失物品
                   AccountKillMailItem mailItem = new AccountKillMailItem();
                   mailItem.setKillMailId(accountKillMail.getId());
                   mailItem.setTypeId(item.getItemTypeId());
                   mailItem.setName(typeMap.getOrDefault(item.getItemTypeId(), new Type()).getName());
                   mailItem.setNum(item.getQuantityDropped() == null ? item.getQuantityDestroyed() : item.getQuantityDropped());
                   mailItem.setType(item.getQuantityDropped() != null ? "销毁" : "掉落");
                   mailItem.setPrice(new BigDecimal(eveCache.getMinSellPrice(item.getItemTypeId())).multiply(new BigDecimal(mailItem.getNum())));
                   itemPrice = itemPrice.add(mailItem.getPrice());
                   accountKillMailItems.add(mailItem);
               }
               if(!accountKillMailItems.isEmpty()){
                   accountKillMailItemMapper.batchInsert(accountKillMailItems);
               }
               itemPrice = itemPrice.add(new BigDecimal(eveCache.getMinSellPrice(accountKillMail.getShipTypeId())));
               accountKillMail.setDamageTaken(itemPrice);
               accountKillMail.updateById();
           }
       }catch (ApiException e){
           log.error(e.getResponseBody(),e);
       }
    }
}
