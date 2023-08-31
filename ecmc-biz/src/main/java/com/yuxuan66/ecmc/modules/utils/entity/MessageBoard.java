package com.yuxuan66.ecmc.modules.utils.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/12/22
 */
@Data
@TableName("corp_message_board")
public class MessageBoard extends BaseEntity<MessageBoard> {

    /**
     * 发布账号
     */
    private Long accountId;

    /**
     * 发布角色名
     */
    private String characterName;
    /**
     * 发布角色ID
     */
    private Integer characterId;
    /**
     * 发布内容
     */
    private String content;
    /**
     * 点赞数量
     */
    private Integer likes;

}
