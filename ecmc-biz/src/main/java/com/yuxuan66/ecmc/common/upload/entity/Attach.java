package com.yuxuan66.ecmc.common.upload.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.ecmc.common.upload.enums.UploadType;
import com.yuxuan66.ecmc.support.base.BaseEntity;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 附件表
 *
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
@Data
@TableName("sys_attach")
public class Attach extends BaseEntity<Attach> {
    /**
     * 文件保存类型
     */
    private UploadType uploadType;

    /**
     * 原始文件名
     */
    private String originalName;
    /**
     * 新文件名
     */
    private String fileName;

    /**
     * 文件保存目录
     */
    private String filePath;

    /**
     * 文件访问URL
     */
    private String url;

    /**
     * 文件MD5
     */
    private String md5;


    @TableField(exist = false)
    private Timestamp updateTime;
    /**
     * 更新人
     */
    @TableField(exist = false)
    private Long updateId;
    /**
     * 更新人
     */
    @TableField(exist = false)
    private String updateBy;
}
