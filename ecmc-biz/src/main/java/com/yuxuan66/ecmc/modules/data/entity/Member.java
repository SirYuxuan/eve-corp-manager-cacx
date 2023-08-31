package com.yuxuan66.ecmc.modules.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.yuxuan66.ecmc.common.utils.excel.annotation.ExcelColumn;
import com.yuxuan66.ecmc.common.utils.excel.handler.BoolHandler;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 军团成员信息表(CorpMember)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-12-24 14:48:05
 */
@Data
@TableName("corp_member")
public class Member extends Model<Member> implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 角色ID
     */
    private Integer characterId;
    /**
     * 角色名称
     */
    @ExcelColumn(name = "角色名", sort = 1)
    private String characterName;
    /**
     * 账号ID
     */
    private Long accountId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户昵称
     */
    @ExcelColumn(name = "所属用户", sort = 2)
    private String nickName;
    /**
     * 是否绑定军团系统
     */
    @ExcelColumn(name = "军团系统", sort = 5, handler = BoolHandler.class, trueVal = "注册", falseVal = "未注册")
    private Boolean corpSystem;
    /**
     * 是否绑定SEAT系统
     */
    @ExcelColumn(name = "联盟系统", sort = 6, handler = BoolHandler.class, trueVal = "注册", falseVal = "未注册")
    private Boolean seatSystem;
    /**
     * 上次上线时间
     */
    @ExcelColumn(name = "上次登录时间", sort = 3)
    private Timestamp lastLoginTime;
    /**
     * 多少天未上线
     */
    @ExcelColumn(name = "未上线天数", sort = 4)
    private Integer notLoginDay;
    private Timestamp createTime;


}

