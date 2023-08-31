package com.yuxuan66.ecmc.support.base.resp;

import cn.hutool.core.lang.Dict;

import java.util.Objects;

/**
 * 标准数据返回对象
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
public class Rs extends Dict {
    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "message";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "result";

    /**
     * 初始化一个新创建的 Rs 对象，使其表示一个空消息。
     */
    public Rs() {
    }


    /**
     * 初始化一个新创建的 Rs 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public Rs(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }


    /**
     * 设置列
     *
     * @param attr  属性
     * @param value 值
     * @return 本身
     */
    public Rs set(String attr, Object value) {
        this.put(attr, value);
        return this;
    }

    /**
     * 初始化一个新创建的 Rs 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public Rs(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (Objects.nonNull(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Rs ok() {
        return Rs.successMsg("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static Rs ok(Object data) {
        return Rs.ok("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static Rs successMsg(String msg) {
        return Rs.ok(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static Rs ok(String msg, Object data) {
        return new Rs(HttpCode.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static Rs error() {
        return Rs.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Rs error(String msg) {
        return Rs.error(msg, null);
    }


    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static Rs error(String msg, Object data) {
        return new Rs(HttpCode.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static Rs error(int code, String msg) {
        return new Rs(code, msg, null);
    }

    /**
     * 返回没有登录的信息
     *
     * @return 警告消息
     */
    public static Rs notLogin(String msg) {
        return new Rs(HttpCode.FORBIDDEN, msg, null);
    }
}
