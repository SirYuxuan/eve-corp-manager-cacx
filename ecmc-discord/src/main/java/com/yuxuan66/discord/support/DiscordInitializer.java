package com.yuxuan66.discord.support;

import cn.hutool.extra.pinyin.PinyinUtil;
import com.yuxuan66.discord.bot.plugin.service.EVEService;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.cache.mapper.TypeAliasMapper;
import com.yuxuan66.ecmc.cache.redis.RedisKit;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2023/1/13
 */
@Component
@RequiredArgsConstructor
public class DiscordInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final EveCache eveCache;
    private final RedisKit redis;
    private final EVEService eveService;
    @Resource
    private TypeAliasMapper typeAliasMapper;
  
    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent applicationReadyEvent) {
        // 处理EVE缓存
        List<Type> typeList = eveCache.getTypeList().stream().filter(item -> item.getMarketGroupId() != null).toList();
        for (Type type : typeList) {
            type.setPinyin(PinyinUtil.getPinyin(type.getName()));
        }
        eveService.setTypeList(typeList);
        // 处理别名缓存
        typeAliasMapper.selectList(null).forEach(item -> redis.hSet(CacheKey.EVE_TYPE_ALIAS, item.getName(), item.getAlias()));

    }
}
