package com.yuxuan66.ecmc.common.utils.excel.annotation;

import cn.hutool.core.util.StrUtil;
import com.yuxuan66.ecmc.common.utils.excel.ExcelHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Sir丶雨轩
 * @since 2022/12/10
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    /**
     * 字段名
     *
     * @return 字段名
     */
    String name();

    /**
     * 字段排序
     *
     * @return 排序
     */
    int sort();

    /**
     * 对象的属性
     *
     * @return 属性名
     */
    String value() default StrUtil.EMPTY;

    /**
     * 用来做对象拼接
     *
     * @return 字段
     */
    String joinName() default StrUtil.EMPTY;


    /**
     * 枚举字段的描述字段
     *
     * @return 描述字段
     */
    String enumName() default "name";

    /**
     * 字段数据需要处理
     *
     * @return 处理程序
     */
    Class<? extends ExcelHandler> handler() default ExcelHandler.class;

    /**
     * {@link com.yuxuan66.ecmc.common.utils.excel.handler.BoolHandler}
     *
     * @return true显示
     */
    String trueVal() default "是";

    /**
     * {@link com.yuxuan66.ecmc.common.utils.excel.handler.BoolHandler}
     *
     * @return false显示
     */
    String falseVal() default "否";


    /**
     * {@link com.yuxuan66.ecmc.common.utils.excel.handler.NumberFormatHandler}
     * 数字格式化方式
     *
     * @return 方式
     */
    String numberFormat() default ",###";

    /**
     * {@link com.yuxuan66.ecmc.common.utils.excel.handler.NumberFormatHandler}
     * 数字格式化后缀
     *
     * @return 后缀
     */
    String numberFormatSuffix() default "";
}
