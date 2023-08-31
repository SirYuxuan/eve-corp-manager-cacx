package com.yuxuan66.ecmc.modules.discord.api.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2023/1/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuildsMembers {

    /**
     * Discord 用户ID
     */
    @JsonIgnore
    private Long userId;
    /**
     * 用户Token
     */
    @JsonProperty("access_token")
    private String accessToken;
    /**
     * 昵称
     */
    private String nick;
    /**
     * 角色列表
     */
    private List<Long> roles;
    /**
     * 是否禁止说话
     */
    private Boolean mute;
    /**
     * 是否禁止听
     */
    private Boolean deaf;
}
