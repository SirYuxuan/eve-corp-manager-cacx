package com.yuxuan66.ecmc.common.utils.excel.handler;

import cn.hutool.core.convert.Convert;
import com.yuxuan66.ecmc.common.utils.excel.ExcelHandler;
import com.yuxuan66.ecmc.common.utils.excel.annotation.ExcelColumn;

/**
 * @author Sir丶雨轩
 * @since 2022/12/12
 */
public class BoolHandler implements ExcelHandler {
    @Override
    public String convert(Object data, ExcelColumn column) {
        return Convert.toBool(data) ? column.trueVal() : column.falseVal();
    }
}
