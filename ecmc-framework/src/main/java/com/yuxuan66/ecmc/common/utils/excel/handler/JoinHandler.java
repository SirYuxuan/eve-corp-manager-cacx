package com.yuxuan66.ecmc.common.utils.excel.handler;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.yuxuan66.ecmc.common.utils.excel.ExcelHandler;
import com.yuxuan66.ecmc.common.utils.excel.annotation.ExcelColumn;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Sir丶雨轩
 * @since 2022/12/10
 */
public class JoinHandler implements ExcelHandler {
    @Override
    public String convert(Object data, ExcelColumn excelColumn) {
        if(data instanceof Collection<?> dataArray){
           return dataArray.stream().map(item-> Convert.toStr(ReflectUtil.getFieldValue(item,excelColumn.joinName()), StrUtil.EMPTY)).collect(Collectors.joining(","));
        }
        return null;
    }
}
