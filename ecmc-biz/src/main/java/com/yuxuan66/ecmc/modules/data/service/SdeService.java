package com.yuxuan66.ecmc.modules.data.service;

import cn.hutool.core.convert.Convert;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sir丶雨轩
 * @since 2022/12/25
 */
@Service
@RequiredArgsConstructor
public class SdeService {

    private final EveCache eveCache;
    /**
     * 模糊查询舰船列表
     *
     * @param shipName 舰船名称
     * @return 舰船列表
     */
    public List<Type> shipList(String shipName) {
        return eveCache.getTypeList().stream().filter(item -> item.getName() != null && (item.getName().contains(shipName) || item.getId().equals(Convert.toInt(shipName,-1))) && "舰船".equals(item.getCategoryName())).toList();
    }
}
