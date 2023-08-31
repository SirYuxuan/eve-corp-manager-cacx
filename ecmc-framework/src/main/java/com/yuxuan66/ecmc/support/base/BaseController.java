package com.yuxuan66.ecmc.support.base;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@SuppressWarnings("all")
@RequiredArgsConstructor
public class BaseController<S> {

    /**
     * 返回重定向的地址
     *
     * @param url 地址
     * @return 重定向地址
     */
    protected String redirect(String url) {
        return "redirect:" + url;
    }


    @Autowired
    protected S baseService;

}
