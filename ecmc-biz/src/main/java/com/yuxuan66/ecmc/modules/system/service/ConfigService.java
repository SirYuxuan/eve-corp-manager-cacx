package com.yuxuan66.ecmc.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.modules.system.entity.Config;
import com.yuxuan66.ecmc.modules.system.mapper.ConfigMapper;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
@Service
public class ConfigService extends BaseService<Config, ConfigMapper> {


    /**
     * 分页查询系统配置列表
     *
     * @param query 查询条件
     * @return 系统配置
     */
    public Ps list(BaseQuery<Config> query) {
        query.processingBlurry("title","name", "value", "remark");
        QueryWrapper<Config> wrapper = query.getQueryWrapper();
        wrapper.orderByDesc("name");
        return Ps.ok(page(query.getPage(), wrapper));
    }

    /**
     * 添加或修改一个配置
     * @param config 配置对象
     */
    public void addOrEdit(Config config) {
        config.insertOrUpdate();
        redis.hSet(CacheKey.CONFIG, config.getName(), config.getValue());
    }

    /**
     * 删除一组配置
     * @param ids 配置ID列表
     */
    public void del(Set<Long> ids) {
        // 删除对应的缓存
        String[] keys = listByIds(ids).stream().map(Config::getName).toList().toArray(new String[0]);
        redis.hDel(CacheKey.CONFIG, keys);
        removeBatchByIds(ids);
    }

    /**
     * 刷新系统内所有缓存
     */
    public void refresh() {
        redis.del(CacheKey.CONFIG);
        for (Config config : list()) {
            redis.hSet(CacheKey.CONFIG, config.getName(), config.getValue());
        }
    }
}
