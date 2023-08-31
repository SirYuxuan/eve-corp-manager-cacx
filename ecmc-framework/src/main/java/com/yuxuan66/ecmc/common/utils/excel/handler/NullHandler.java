package com.yuxuan66.ecmc.common.utils.excel.handler;

import com.yuxuan66.ecmc.common.utils.excel.ExcelHandler;
import com.yuxuan66.ecmc.common.utils.excel.annotation.ExcelColumn;

/**
 * @author Sir丶雨轩
 * @since 2022/12/12
 */
public class NullHandler implements ExcelHandler {
    @Override
    public String convert(Object data, ExcelColumn column) {
        return data == null ? column.trueVal() : column.falseVal();
    }
}
