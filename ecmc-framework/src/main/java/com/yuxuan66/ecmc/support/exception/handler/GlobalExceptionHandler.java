package com.yuxuan66.ecmc.support.exception.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.hutool.core.convert.Convert;
import com.yuxuan66.ecmc.common.utils.WebUtil;
import com.yuxuan66.ecmc.support.base.resp.HttpCode;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import com.yuxuan66.ecmc.support.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理，返回友好的提示
 *
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 接口不存在提示
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Rs> handleResourceNotFoundException(NoHandlerFoundException e) {
        return buildResponseEntity(Rs.error("接口" + e.getRequestURL() + ", 不存在"));
    }


    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Rs> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return buildResponseEntity(Rs.error(message));
    }

    /**
     * 没有登录
     * @param e 错误信息
     * @return 没有登录
     */
    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<Rs> notLoginException(NotLoginException e) {
        return buildResponseEntity(Rs.error("您还没有登录,请先登录系统").set(Rs.CODE_TAG, HttpCode.UNAUTHORIZED));
    }

    /**
     * 统一返回
     */
    private ResponseEntity<Rs> buildResponseEntity(Rs respEntity) {
        return new ResponseEntity<>(respEntity, HttpStatus.OK);
    }

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Rs> handleException(Throwable e) {
        e.printStackTrace();
        String message = Convert.toStr(e.getMessage(), "服务器异常,请稍后重试...");


        if (e.getClass().equals(BizException.class)) {
            return buildResponseEntity(Rs.error(e.getMessage()));
        }
        // 数据库执行报错
        String dbImplementError = "Error updating database";
        if (message.contains(dbImplementError)) {
            return buildResponseEntity(Rs.error("服务器异常,数据库执行失败..."));
        }

        if (e.getClass().equals(HttpRequestMethodNotSupportedException.class)) {
            HttpRequestMethodNotSupportedException methodNotSupportedException = (HttpRequestMethodNotSupportedException) e;
            return buildResponseEntity(Rs.error("无法找到接口" + WebUtil.getRequest().getRequestURI() + "的" + methodNotSupportedException.getMethod() + "请求方式"));
        }

        return buildResponseEntity(Rs.error(("服务器异常,请稍后重试...")));
    }
}
