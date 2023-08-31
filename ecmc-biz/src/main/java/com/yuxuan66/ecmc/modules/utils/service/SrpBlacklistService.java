package com.yuxuan66.ecmc.modules.utils.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.mapper.UserAccountMapper;
import com.yuxuan66.ecmc.modules.utils.entity.SrpBlacklist;
import com.yuxuan66.ecmc.modules.utils.entity.SrpRules;
import com.yuxuan66.ecmc.modules.utils.mapper.SrpBlacklistMapper;
import com.yuxuan66.ecmc.modules.utils.mapper.SrpRulesMapper;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Sir丶雨轩
 * @since 2022/12/25
 */
@Service
@RequiredArgsConstructor
public class SrpBlacklistService extends BaseService<SrpBlacklist, SrpBlacklistMapper> {

    private final EveCache eveCache;

    @Resource
    private UserAccountMapper userAccountMapper;

    /**
     * 分页查询补损黑名单
     * @param query 查询条件
     * @return 黑名单
     */
    public Ps list(BaseQuery<SrpBlacklist> query){
        query.processingBlurry("name");
        return Ps.ok(page(query.getPage(),query.getQueryWrapper()));
    }

    /**
     * 新增或编辑一个补损黑名单
     * @param srpBlacklist 补损黑名单
     */
    public void addOrEdit(SrpBlacklist srpBlacklist){
        List<UserAccount> userAccountList = userAccountMapper.selectList(new QueryWrapper<UserAccount>().eq("character_id", srpBlacklist.getCharacterId()));
        if(userAccountList.isEmpty()){
            throw new BizException("没有找到此角色");
        }
        srpBlacklist.setName(userAccountList.get(0).getCharacterName());
        srpBlacklist.setUserId(userAccountList.get(0).getUserId());
        srpBlacklist.insertOrUpdate();
    }

    /**
     * 批量删除一组补损黑名单
     * @param ids 黑名单ID
     */
    public void del(Set<Long> ids){
        removeBatchByIds(ids);
    }
}
