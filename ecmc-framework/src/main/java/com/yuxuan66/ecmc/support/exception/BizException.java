package com.yuxuan66.ecmc.support.exception;


/**
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public class BizException extends RuntimeException {

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Exception e) {
        super(message, e);
    }
}
