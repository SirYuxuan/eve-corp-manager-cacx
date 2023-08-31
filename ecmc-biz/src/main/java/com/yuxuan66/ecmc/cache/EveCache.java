package com.yuxuan66.ecmc.cache;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.yuxuan66.ecmc.cache.entity.Address;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.cache.mapper.AddressMapper;
import com.yuxuan66.ecmc.cache.mapper.TypeMapper;
import com.yuxuan66.ecmc.cache.redis.RedisKit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.troja.eve.esi.api.AllianceApi;
import net.troja.eve.esi.api.CharacterApi;
import net.troja.eve.esi.api.CorporationApi;
import net.troja.eve.esi.api.UniverseApi;
import net.troja.eve.esi.model.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sir丶雨轩
 * @since 2022/12/22
 */
@Component
@RequiredArgsConstructor
public class EveCache {
    private final RedisKit redis;

    @Resource
    private TypeMapper typeMapper;
    @Resource
    private AddressMapper addressMapper;

    /**
     * 获取sde中的物品类型
     *
     * @return 所有物品类型
     */
    @Cacheable(cacheNames = "ESI:SDE:TYPE", key = "'LIST'")
    public List<Type> getTypeList() {
        return typeMapper.selectList(null);
    }

    /**
     * 获取sde中的地址信息
     *
     * @return 所有地址信息
     */
    @Cacheable(cacheNames = "ESI:SDE:ADDRESS", key = "'LIST'")
    public List<Address> getAddressList() {
        return addressMapper.selectList(null);
    }

    /**
     * TODO 这个需要自己维护
     * 获取吉他最低销售价
     *
     * @param id 物品id
     * @return 价格
     */
    public long getMinSellPrice(Integer id) {

        return JSONObject.parseArray(HttpUtil.get("https://api.evemarketer.com/ec/marketstat/json?typeid="+id+"&regionlimit=10000002&usesystem=30000142")).getJSONObject(0)
                .getJSONObject("sell")
                .getLongValue("min");
        //return JSONObject.parseObject(HttpUtil.get("https://www.ceve-market.org/tqapi/market/region/10000002/system/30000142/type/" + id + ".json")).getJSONObject("sell").getLongValue("min");
    }

    /**
     * TODO 这个需要自己维护
     * 获取吉他最高收购
     *
     * @param id 物品id
     * @return 价格
     */
    public long getMaxBuyPrice(Integer id) {
        return JSONObject.parseArray(HttpUtil.get("https://api.evemarketer.com/ec/marketstat/json?typeid="+id+"&regionlimit=10000002&usesystem=30000142")).getJSONObject(0)
                .getJSONObject("buy")
                .getLongValue("max");
    }

    /**
     * 获取sde重的物品类型
     *
     * @return 物品类型
     */
    @Cacheable(cacheNames = "ESI:SDE:TYPE", key = "'MAP'")
    public Map<Integer, Type> getTypeMap() {
        List<Type> typeList = getTypeList();
        Map<Integer, Type> typeMap = new HashMap<>(typeList.size());
        for (Type type : typeList) {
            typeMap.put(type.getId(), type);
        }
        return typeMap;
    }

    /**
     * 获取一个物品得信息
     *
     * @param typeId 物品ID
     * @return 物品信息
     */
    @SneakyThrows
    @Cacheable(cacheNames = "ESI:API:TypeName", key = "#typeId")
    public TypeResponse typeName(int typeId) {
        return new UniverseApi().getUniverseTypesTypeId(typeId, "zh", null, null, "zh");
    }

    /**
     * 获取指定区域的名称
     *
     * @param regionId 区域ID
     * @return 星系名称
     */
    @SneakyThrows
    @Cacheable(cacheNames = "ESI:API:RegionName", key = "#regionId")
    public RegionResponse getRegion(int regionId) {
        return new UniverseApi().getUniverseRegionsRegionId(regionId, "zh", "", null, "zh");
    }

    /**
     * 获取空间站的名称
     *
     * @param stationsId 区域ID
     * @return 星系名称
     */
    @SneakyThrows
    @Cacheable(cacheNames = "ESI:API:Stations", key = "#stationsId")
    public StationResponse getStations(int stationsId) {
        return new UniverseApi().getUniverseStationsStationId(stationsId, null, null);
    }

    /**
     * 获取玩家建筑的名称
     *
     * @param structureId 建筑id
     * @return 建筑名称
     */
    @SneakyThrows
    public String getStructures(long structureId, int characterId, String token) {
        try {
            String cacheKey = "ESI:API:NEWStructuresName:" + characterId + ":" + structureId;
            if (redis.exist(cacheKey)) {
                return redis.get(cacheKey);
            }
            String name = new UniverseApi().getUniverseStructuresStructureId(structureId, null, null, token).getName();
            redis.set(cacheKey, name);
            return name;
        } catch (Exception e) {
            return "未知";
        }
    }

    /**
     * 获取指定星系的名称
     *
     * @param solarSystemId 星系ID
     * @return 星系名称
     */
    @SneakyThrows
    @Cacheable(cacheNames = "ESI:API:SolarSystemName", key = "#solarSystemId")
    public SystemResponse getSolarSystemName(int solarSystemId) {
        return new UniverseApi().getUniverseSystemsSystemId(solarSystemId, "zh", "", null, "zh");
    }

    /**
     * 获取指定角色的基本信息
     *
     * @param characterId 角色ID
     * @return 基本信息
     */
    @SneakyThrows
    @Cacheable(cacheNames = "ESI:API:CharacterInfo", key = "#characterId")
    public CharacterResponse getCharacterInfo(int characterId) {
        return new CharacterApi().getCharactersCharacterId(characterId, null, null);
    }

    /**
     * 获取指定军团的基本信息
     *
     * @param corpId 军团ID
     * @return 基本信息
     */
    @SneakyThrows
    @Cacheable(cacheNames = "ESI:API:CorporationInfo", key = "#corpId")
    public CorporationResponse getCorporationInfo(int corpId) {
        return new CorporationApi().getCorporationsCorporationId(corpId, null, null);
    }

    /**
     * 获取指定联盟的基本信息
     *
     * @param allianceId 联盟ID
     * @return 基本信息
     */
    @SneakyThrows
    @Cacheable(cacheNames = "ESI:API:AllianceInfo", key = "#allianceId")
    public AllianceResponse getAllianceInfo(int allianceId) {
        return new AllianceApi().getAlliancesAllianceId(allianceId, null, null);
    }
}
