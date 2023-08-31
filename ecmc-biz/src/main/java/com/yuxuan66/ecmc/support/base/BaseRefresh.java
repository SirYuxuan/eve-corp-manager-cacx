package com.yuxuan66.ecmc.support.base;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.PageUtil;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import net.troja.eve.esi.ApiException;
import net.troja.eve.esi.api.UniverseApi;
import net.troja.eve.esi.model.UniverseNamesResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Sir丶雨轩
 * @since 2022/12/27
 */
public class BaseRefresh {

    @Resource
    private EveCache eveCache;

    /**
     * 查询一个物品的名称
     * @param typeMap 物品数据
     * @param typeId 物品ID
     * @return 名称
     */
    protected String typeName(Map<Integer, Type> typeMap, int typeId){
        if (typeMap.containsKey(typeId)) {
           return typeMap.get(typeId).getName();
        } else {
           return eveCache.typeName(typeId).getName();
        }
    }

    /**
     * 查询ID的名称
     * @param ids id列表
     * @return 名称
     * @throws ApiException  ApiException
     */
    protected List<UniverseNamesResponse> universeNames(List<Integer> ids) throws ApiException {
        List<UniverseNamesResponse> result = new ArrayList<>();
        if(ids.isEmpty()){
            return result;
        }
        if(!ids.isEmpty()){
            if (ids.size() > 1000) {
                int totalPage = PageUtil.totalPage(ids.size(), 1000);
                for (int i = 0; i < totalPage; i++) {
                    List<Integer> tempIds = ListUtil.page(i, 1000, ids);
                    result.addAll(new UniverseApi().postUniverseNames(new HashSet<>(tempIds).stream().toList(),null));
                }
            }else{
                result = new UniverseApi().postUniverseNames(new HashSet<>(ids).stream().toList(),null);
            }
        }
        return result;
    }
}
