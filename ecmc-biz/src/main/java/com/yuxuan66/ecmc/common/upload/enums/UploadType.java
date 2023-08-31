package com.yuxuan66.ecmc.common.upload.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Objects;

/**
 * 文件上传类型
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
public enum UploadType {

    /**
     * 上传本地文件
     */
    LOCAL(1,"本地"),
    /**
     * 上传阿里云
     */

    ALI_OSS(2,"阿里云OSS")

    ;
    @JsonValue
    @EnumValue
    private final Integer value;
    private final String name;

    UploadType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer value() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }


    public boolean equals(Integer status) {
        return Objects.equals(status, this.value);
    }

    /**
     * 根据类型获取枚举
     * @param value 类型
     * @return 枚举
     */
    public static UploadType getByVal(int value){
        return Arrays.stream(UploadType.values()).filter(item -> item.value == value).findAny().orElse(null);
    }
}
