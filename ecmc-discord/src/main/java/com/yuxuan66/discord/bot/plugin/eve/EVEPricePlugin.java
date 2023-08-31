package com.yuxuan66.discord.bot.plugin.eve;

import com.yuxuan66.discord.bot.plugin.ReConst;
import com.yuxuan66.discord.bot.plugin.definition.MessagePlugin;
import com.yuxuan66.discord.bot.plugin.definition.PluginInfo;
import com.yuxuan66.discord.bot.plugin.service.EVEService;
import lombok.RequiredArgsConstructor;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir丶雨轩
 * @since 2023/1/12
 */
@Component
@RequiredArgsConstructor
public class EVEPricePlugin implements MessagePlugin {

    private final EVEService eveService;

    @Override
    public PluginInfo info() {
        return new PluginInfo("EVE信息查询", 1);
    }

    @Override
    public Matcher match(String message) {
        return Pattern.compile(ReConst.PRICE, Pattern.CASE_INSENSITIVE).matcher(message);
    }

    @Override
    public boolean apply(MessageCreateEvent event, Matcher matcher, String message) {
        // 匹配是否是价格查询
        String instructions = matcher.group(1).trim();
        String name = matcher.group(2).trim();
        if(name.length() == 0){
            return true;
        }
        boolean isEur = !instructions.contains("g");
        boolean isCol = instructions.contains("col");
        eveService.price(event, name, isEur, isCol);
        return false;
    }
}
