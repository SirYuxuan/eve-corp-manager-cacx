package com.yuxuan66.ecmc.modules.utils.entity;

import java.sql.Timestamp;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yuxuan66.ecmc.modules.account.entity.AccountKillMail;
import com.yuxuan66.ecmc.modules.utils.entity.consts.SrpLogStatus;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 军团补损申请记录(CorpSrpLog)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-12-23 12:00:07
 */
@Data
@TableName("corp_srp_log")
public class SrpLog extends BaseEntity<SrpLog> implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 击毁邮件ID
     */
    private Long killMailId;
    /**
     * 状态1=待审批，2=已通过，3=已拒绝
     */
    private SrpLogStatus status = SrpLogStatus.WAIT;
    /**
     * 提交备注
     */
    private String content;
    /**
     * 审批备注
     */
    private String spContent;

    /**
     * 用户击杀信息
     */
    @TableField(exist = false)
    private AccountKillMail accountKillMail;



}

