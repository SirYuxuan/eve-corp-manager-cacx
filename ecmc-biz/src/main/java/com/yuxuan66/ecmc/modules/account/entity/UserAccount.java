package com.yuxuan66.ecmc.modules.account.entity;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.ecmc.cache.ConfigKit;
import com.yuxuan66.ecmc.cache.key.CacheKey;
import com.yuxuan66.ecmc.common.esi.EsiHelper;
import com.yuxuan66.ecmc.common.utils.excel.annotation.ExcelColumn;
import com.yuxuan66.ecmc.common.utils.excel.handler.BoolHandler;
import com.yuxuan66.ecmc.common.utils.excel.handler.NullHandler;
import com.yuxuan66.ecmc.common.utils.excel.handler.NumberFormatHandler;
import com.yuxuan66.ecmc.modules.system.entity.User;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.troja.eve.esi.ApiClient;
import net.troja.eve.esi.auth.JWT;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 军团用户角色表(CorpUserAccount)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-12-12 16:08:00
 */
@Slf4j
@Data
@ToString
@TableName("corp_user_account")
public class UserAccount extends BaseEntity<UserAccount> implements Serializable {
    @Serial
    private static final long serialVersionUID = -80069397898096666L;

    /**
     * 角色名称
     */
    @ExcelColumn(name = "角色名", sort = 1)
    private String characterName;
    /**
     * 角色ID
     */
    private Integer characterId;
    /**
     * 系统用户ID
     */
    private Long userId;
    /**
     * 主角色
     */
    @ExcelColumn(name = "主角色", sort = 7, handler = BoolHandler.class)
    private Boolean isMain;
    /**
     * ESI/Token
     */
    @ExcelColumn(name = "ESI状态", sort = 4, handler = NullHandler.class, trueVal = "ERROR", falseVal = "CACX FULL")
    private String accessToken;
    /**
     * AccessToken过期时间
     */
    private Timestamp accessExp;
    /**
     * ESI/Token
     */
    private String refreshToken;
    /**
     * 军团ID
     */
    private Integer corpId;
    /**
     * 军团名称
     */
    @ExcelColumn(name = "军团", sort = 2)
    private String corpName;
    /**
     * 联盟ID
     */
    private Integer allianceId;
    /**
     * 联盟名称
     */
    @ExcelColumn(name = "联盟", sort = 3)
    private String allianceName;
    /**
     * ISK数量
     */
    @ExcelColumn(name = "ISK", sort = 5, handler = NumberFormatHandler.class, numberFormatSuffix = " ISK")
    private Double isk;
    /**
     * 技能点数量
     */
    @ExcelColumn(name = "技能点", sort = 6, handler = NumberFormatHandler.class, numberFormatSuffix = " SP")
    private Long skill;
    /**
     * LP当前数量
     */
    private BigDecimal lpNow;
    /**
     * LP总计获得数量
     */
    private BigDecimal lpTotal;
    /**
     * LP已使用数量
     */
    private BigDecimal lpUse;
    /**
     * 入团时间(加入主军团的时间)
     */
    private Timestamp joinTime;

    /**
     * 未分配技能点
     */
    private Integer unallocatedSp;

    @TableField(exist = false)
    private User user;



    /**
     * 获取一个esi操作对象
     *
     * @return ESI客户端
     */
    public ApiClient esiClient() {
        try {
            // 创建ESI对象,刷新Token
            ApiClient client = EsiHelper.newClient();
            client.setAccessToken(this.getAccessToken());
            int minute = 1000 * 60 * 3;
            if (this.getAccessExp() != null && this.getAccessExp().getTime() - minute > System.currentTimeMillis()) {
                return client;
            }
            // 刷新Token
            HttpRequest request = HttpUtil.createPost("https://login.eveonline.com/v2/oauth/token");
            request.form("grant_type", "refresh_token");
            request.form("refresh_token", this.getRefreshToken());
            request.header("Authorization", "Basic " + Base64.encode(ConfigKit.get(CacheKey.EVE_ESI_CLIENT_ID) + ":" + ConfigKit.get(CacheKey.EVE_ESI_SECRET_KEY)));
            HttpResponse response = request.execute();
            JSONObject tokenInfo = JSONObject.parseObject(response.body());

            if (tokenInfo == null) {
                log.warn("用户TOKEN刷新失败," + this.getCharacterName());
                return client;
            }
            this.setRefreshToken(tokenInfo.getString("refresh_token"));
            this.setAccessToken(tokenInfo.getString("access_token"));
            client.setAccessToken(this.getAccessToken());
            JWT authJwt = EsiHelper.getJWT(this.getAccessToken());
            if (authJwt != null) {
                this.setCharacterId(authJwt.getPayload().getCharacterID());
                this.setCharacterName(authJwt.getPayload().getName());
                this.setAccessExp(new Timestamp(Convert.toLong(authJwt.getPayload().getExp()) * 1000));
            }
            this.updateById();
            client.setDebugging(true);
            client.setAccessToken(this.getAccessToken());
            return client;
        } catch (Exception e) {
            e.printStackTrace();
            return EsiHelper.defaultClient();
        }


    }


}

