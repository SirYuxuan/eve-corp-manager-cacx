package com.yuxuan66.ecmc.modules.discord.api.forest.annotation;

import com.dtflys.forest.annotation.MethodLifeCycle;
import com.dtflys.forest.annotation.RequestAttributes;
import com.yuxuan66.ecmc.modules.discord.api.forest.HttpTokenLifeCycle;

import java.lang.annotation.*;

/**
 * @author Sir丶雨轩
 * @since 2023/1/11
 */
@Documented
@MethodLifeCycle(HttpTokenLifeCycle.class)
@RequestAttributes
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD})
public @interface Token {
}
