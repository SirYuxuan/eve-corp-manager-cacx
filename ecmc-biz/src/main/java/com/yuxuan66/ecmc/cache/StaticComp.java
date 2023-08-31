package com.yuxuan66.ecmc.cache;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.extra.spring.SpringUtil;
import com.yuxuan66.ecmc.cache.redis.RedisKit;
import com.yuxuan66.ecmc.common.upload.mapper.AttachMapper;
import com.yuxuan66.ecmc.modules.system.mapper.ConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 全局静态组件初始化
 * @author Sir丶雨轩
 * @since 2022/12/12
 */
@Slf4j
@Component
public class StaticComp {


    /**
     * redis操作工具库
     */
    public static RedisKit redisKit;
    /**
     * 系统配置操作mapper
     */
    public static ConfigMapper configMapper;

    /**
     * 附件操作对象
     */
    public static AttachMapper attachMapper;
    /**
     * 处理系统内的全局静态组件
     */
    @PostConstruct
    public void initCache() {
        TimeInterval timer = DateUtil.timer();
        log.info("开始初始化全局静态组件...");
        redisKit = SpringUtil.getBean(RedisKit.class);
        log.info("redisKit[{}] init success...", redisKit.toString());
        configMapper = SpringUtil.getBean(ConfigMapper.class);
        log.info("configMapper[{}] init success...", configMapper.toString());
        attachMapper = SpringUtil.getBean(AttachMapper.class);
        log.info("attachMapper[{}] init success...", attachMapper.toString());
        log.info("全局静态组件初始化完成, 耗时：{}",timer.intervalPretty());
    }
}
