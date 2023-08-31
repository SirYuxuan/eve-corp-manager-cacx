package com.yuxuan66.ecmc.bot.qq.plugin.impl;

import com.yuxuan66.ecmc.bot.qq.ReConst;
import com.yuxuan66.ecmc.bot.qq.plugin.definition.MessagePlugin;
import com.yuxuan66.ecmc.bot.qq.plugin.definition.PluginInfo;
import com.yuxuan66.ecmc.modules.account.service.OpenAccountApiService;
import com.yuxuan66.ecmc.modules.system.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import net.mamoe.mirai.event.events.MessageEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir丶雨轩
 * @since 2023/2/9
 */
@Component
@RequiredArgsConstructor
public class LpPlugin implements MessagePlugin {

    private final OpenAccountApiService openAccountApiService;
    @Resource
    private UserMapper userMapper;
    @Override
    public Matcher match(String message) {
        return Pattern.compile(ReConst.LP, Pattern.CASE_INSENSITIVE).matcher(message);
    }
    @Override
    public PluginInfo info() {
        return new PluginInfo("LP查询", 3);
    }
    @Override
    public boolean apply(MessageEvent event, Matcher matcher, String message) {
        event.getSubject().sendMessage(openAccountApiService.lp(event.getSender().getId()));
        return false;
    }
}
