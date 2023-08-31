package com.yuxuan66.ecmc.common.utils.tree;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示树的节点ID
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TreeId {

    /**
     * 默认的id
     * @return id
     */
    long defaultId() default 0L;

    /**
     * 是否是父级id
     * @return 是否
     */
    boolean isParent() default false;
}
