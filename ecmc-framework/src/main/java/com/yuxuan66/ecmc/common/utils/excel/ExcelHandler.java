package com.yuxuan66.ecmc.common.utils.excel;

import com.yuxuan66.ecmc.common.utils.excel.annotation.ExcelColumn;

/**
 * @author Sir丶雨轩
 * @since 2022/5/19
 */
public interface ExcelHandler {

    /**
     * 转换数据
     *
     * @param data 数据转换
     * @param column 注解数据
     * @return 转换后数据
     */
    String convert(Object data, ExcelColumn column);
}
