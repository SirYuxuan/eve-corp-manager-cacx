package com.yuxuan66.ecmc.modules.discord.api.forest.annotation;

import com.dtflys.forest.annotation.MethodLifeCycle;
import com.dtflys.forest.annotation.RequestAttributes;
import com.yuxuan66.ecmc.modules.discord.api.forest.BotTokenLifeCycle;

import java.lang.annotation.*;

/**
 * @author Sir丶雨轩
 * @since 2023/1/12
 */
@Documented
@MethodLifeCycle(BotTokenLifeCycle.class)
@RequestAttributes
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD})
public @interface BotToken {
}
