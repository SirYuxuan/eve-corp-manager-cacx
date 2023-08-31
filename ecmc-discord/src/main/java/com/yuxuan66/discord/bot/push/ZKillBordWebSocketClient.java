package com.yuxuan66.discord.bot.push;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.http.HtmlUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.Type;
import lombok.extern.slf4j.Slf4j;
import net.troja.eve.esi.ApiException;
import net.troja.eve.esi.api.CharacterApi;
import net.troja.eve.esi.model.CharacterResponse;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sir丶雨轩
 * @since 2023/1/13
 */
@Slf4j
public class ZKillBordWebSocketClient extends WebSocketClient {

    private final DiscordApi discordApi;
    private final EveCache eveCache;
    public static boolean WEB_STATUS = false;
    public ZKillBordWebSocketClient(URI serverUri, DiscordApi discordApi, EveCache eveCache) {
        super(serverUri);
        this.discordApi = discordApi;
        this.eveCache = eveCache;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        Map<String, Object> param = new HashMap<>();
        param.put("action", "sub");
        param.put("channel", "killstream");
        send(JSON.toJSONString(param));
        log.info("ZKillBordWebSocket Connected.");
        WEB_STATUS = true;
    }

    @Override
    public void onMessage(String s) {
        try {
            //log.info("收到KM信息: " + s);
            String targetId = "1062933568982089748";
            JSONObject kmInfo = JSONObject.parseObject(s);
            int characterId = kmInfo.getJSONObject("victim").getIntValue("character_id");
            if (characterId == 0) {
                return;
            }
            CharacterResponse characterInfo = new CharacterApi().getCharactersCharacterId(characterId, null, null);
            String characterName = characterInfo.getName();
            String characterTitle = HtmlUtil.removeAllHtmlAttr(Convert.toStr(characterInfo.getTitle(), "无"));
            int corporationId = characterInfo.getCorporationId();
            int allianceId = Convert.toInt(characterInfo.getAllianceId(), 0);

            String lastCharacterName = "";
            int lastShipId = 0;
            int lastTypeId = 0;
            int lastAlianceId = 0;
            int lastCorpId = 0;
            // 获取最后一击数据
            for (Object obj : kmInfo.getJSONArray("attackers")) {
                JSONObject attackers = (JSONObject) obj;
                if (attackers.getBooleanValue("final_blow")) {
                    if (attackers.getIntValue("character_id") == 0) {
                        lastCharacterName = "NPC";
                    } else {
                        CharacterResponse characterResponse = new CharacterApi().getCharactersCharacterId(attackers.getIntValue("character_id"), null, null);
                        lastCharacterName = characterResponse.getName();
                        lastCorpId = characterResponse.getCorporationId();
                        lastAlianceId = Convert.toInt(characterResponse.getAllianceId(), 0);
                    }

                    lastTypeId = attackers.getIntValue("weapon_type_id");
                    lastShipId = attackers.getIntValue("ship_type_id");

                }
            }

            double totalValue = kmInfo.getJSONObject("zkb").getDoubleValue("totalValue");
            // 如果死亡的人员所属军团，或最终击杀人员属于联盟，则推送
            if (allianceId != 99003581 && lastAlianceId != 99003581 && totalValue < 2000000000) {
                return;
            }

            if (lastCorpId == 98598862 || corporationId == 98598862) {
                targetId = "1062933005276041226";
            }

            if (totalValue > 2000000000) {
                targetId = "1062933767041339512";
            }
            String solrSystem = eveCache.getSolarSystemName(kmInfo.getIntValue("solar_system_id")).getName();

            String corporationName = eveCache.getCorporationInfo(corporationId).getName();
            String allianceName = allianceId == 0 ? "无联盟" : eveCache.getAllianceInfo(allianceId).getName();

            int shipId = kmInfo.getJSONObject("victim").getIntValue("ship_type_id");
            Type ship = eveCache.getTypeMap().get(shipId);
            String kmUrl = kmInfo.getJSONObject("zkb").getString("url");

            String totalPrice = NumberUtil.decimalFormat(",###", totalValue / 1000000.00) + " M ISK";
            final Color color = lastAlianceId == 99003581 ? Color.GREEN : Color.ORANGE;
            String finalLastCharacterName = lastCharacterName;
            int finalLastShipId = lastShipId;
            discordApi.getChannelById(targetId).flatMap(Channel::asServerTextChannel).ifPresent(channel -> {
                new MessageBuilder()
                        .setEmbed(new EmbedBuilder()
                                .setAuthor(characterName + " | " + ship.getName() + " | " + solrSystem, kmUrl, "")
                                .addField("", "角色: " + characterName)
                                .addField("", "头衔: " + characterTitle)
                                .addField("", "公司: " + corporationName)
                                .addField("", "联盟: " + allianceName)
                                .addField("", "星系: " + solrSystem)
                                .addField("", "总价: " + totalPrice)
                                .addField("", "时间: " + DateUtil.formatDateTime(DateUtil.offsetHour(new Date(kmInfo.getTimestamp("killmail_time").getTime()), 8)))
                                .setColor(color)
                                .setFooter("最终一击: " + finalLastCharacterName, "https://images.evetech.net/types/" + finalLastShipId + "/render?size=256")
                                .setThumbnail("https://images.evetech.net/Type/" + ship.getId() + "_64.png")
                        )
                        .send(channel);
            });
        } catch (ApiException e) {
            log.error(e.getResponseBody(), e);
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        WEB_STATUS = false;
    }

    @Override
    public void onError(Exception e) {
        WEB_STATUS = false;
        log.error("WebSocket Error,", e);
    }


}
