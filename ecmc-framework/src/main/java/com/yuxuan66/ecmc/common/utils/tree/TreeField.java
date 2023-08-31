package com.yuxuan66.ecmc.common.utils.tree;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示树的节点
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TreeField {

    /**
     * 树中名称
     *
     * @return 默认取字段名称
     */
    String value() default "";

    /**
     * 模仿某个字段
     * @return 模仿某个字段
     */
    String like() default "";

    /**
     * 仅第一级显示
     * @return 仅第一级显示
     */
    boolean onlyFirst() default false;

    /**
     * 二级
     * @return 二级
     */
    String secondLevel() default "";

    /**
     * 二级名称
     * @return 二级名称
     */
    String secondLevelName() default "";

    /**
     * 是否是枚举
     * @return 枚举
     */
    boolean isEnum() default false;
}
