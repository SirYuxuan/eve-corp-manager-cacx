package com.yuxuan66.ecmc.modules.discord.api.entity;

import com.alibaba.fastjson2.JSON;
import com.yuxuan66.ecmc.common.utils.Lang;

/**
 * @author Sir丶雨轩
 * @since 2023/1/11
 */
public class BaseDiscordEntity {


    public String toBody(){
        return Lang.buildQuery(JSON.parseObject(JSON.toJSONString(this)));
    }
}
