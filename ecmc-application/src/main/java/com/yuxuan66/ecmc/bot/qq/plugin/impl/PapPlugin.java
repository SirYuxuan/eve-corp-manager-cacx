package com.yuxuan66.ecmc.bot.qq.plugin.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.bot.qq.ReConst;
import com.yuxuan66.ecmc.bot.qq.plugin.definition.MessagePlugin;
import com.yuxuan66.ecmc.bot.qq.plugin.definition.PluginInfo;
import com.yuxuan66.ecmc.modules.account.service.OpenAccountApiService;
import com.yuxuan66.ecmc.modules.system.entity.User;
import com.yuxuan66.ecmc.modules.system.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import net.mamoe.mirai.event.events.MessageEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir丶雨轩
 * @since 2023/2/9
 */
@Component
@RequiredArgsConstructor
public class PapPlugin implements MessagePlugin {

    private final OpenAccountApiService openAccountApiService;

    @Override
    public Matcher match(String message) {
        return Pattern.compile(ReConst.PAP, Pattern.CASE_INSENSITIVE).matcher(message);
    }
    @Override
    public PluginInfo info() {
        return new PluginInfo("PAP查询", 4);
    }
    @Override
    public boolean apply(MessageEvent event, Matcher matcher, String message) {
        event.getSubject().sendMessage(openAccountApiService.pap(Convert.toLong(event.getSender().getId())));
        return false;
    }
}
