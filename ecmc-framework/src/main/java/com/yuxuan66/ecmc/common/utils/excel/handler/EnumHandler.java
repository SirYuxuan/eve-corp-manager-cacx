package com.yuxuan66.ecmc.common.utils.excel.handler;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import com.yuxuan66.ecmc.common.utils.excel.ExcelHandler;
import com.yuxuan66.ecmc.common.utils.excel.annotation.ExcelColumn;

/**
 * @author Sir丶雨轩
 * @since 2022/12/10
 */
public class EnumHandler implements ExcelHandler {
    @Override
    public String convert(Object data, ExcelColumn excelColumn) {
        return Convert.toStr(ReflectUtil.getFieldValue(data,"name"));
    }
}
