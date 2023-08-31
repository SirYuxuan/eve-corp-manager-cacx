package com.yuxuan66.ecmc.modules.utils.service;

import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.modules.utils.entity.SrpRules;
import com.yuxuan66.ecmc.modules.utils.mapper.SrpRulesMapper;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * @author Sir丶雨轩
 * @since 2022/12/25
 */
@Service
@RequiredArgsConstructor
public class SrpRulesService extends BaseService<SrpRules, SrpRulesMapper> {

    private final EveCache eveCache;

    /**
         * 分页查询SRP规则
         * @param query 查询条件
         * @return SRP规则
         */
        public Ps list(BaseQuery<SrpRules> query){
            query.processingBlurry("ship_name");
            return Ps.ok(page(query.getPage(),query.getQueryWrapper()));
        }

        /**
         * 新增或编辑一个补损规则
         * @param srpRules 补损规则
         */
        public void addOrEdit(SrpRules srpRules){
            Map<Integer, Type> typeMap = eveCache.getTypeMap();
            if(typeMap.containsKey(srpRules.getShipId())){
                srpRules.setShipName(typeMap.get(srpRules.getShipId()).getName());
            }
            srpRules.insertOrUpdate();
        }

        /**
         * 批量删除一组补损规则
         * @param ids 规则ID
         */
        public void del(Set<Long> ids){
            removeBatchByIds(ids);
    }
}
