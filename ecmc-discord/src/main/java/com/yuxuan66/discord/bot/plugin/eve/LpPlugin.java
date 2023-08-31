package com.yuxuan66.discord.bot.plugin.eve;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.discord.bot.plugin.ReConst;
import com.yuxuan66.discord.bot.plugin.definition.MessagePlugin;
import com.yuxuan66.discord.bot.plugin.definition.PluginInfo;
import com.yuxuan66.ecmc.modules.account.service.OpenAccountApiService;
import com.yuxuan66.ecmc.modules.system.entity.User;
import com.yuxuan66.ecmc.modules.system.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.javacord.api.event.message.MessageCreateEvent;
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
    public boolean apply(MessageCreateEvent event, Matcher matcher, String message) {
        // 获取发言人对应的QQ
        List<User> userList = userMapper.selectList(new QueryWrapper<User>().eq("discord_id", event.getMessage().getAuthor().getId()));
        if(userList.isEmpty()){
            event.getChannel().sendMessage("数据异常,无法查找到您绑定的军团用户");
            return false;
        }
        event.getChannel().sendMessage(openAccountApiService.lp(Convert.toLong(userList.get(0).getQq())));
        return false;
    }
}
