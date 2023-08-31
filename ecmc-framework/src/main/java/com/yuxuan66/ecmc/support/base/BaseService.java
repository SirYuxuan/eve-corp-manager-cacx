package com.yuxuan66.ecmc.support.base;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONPath;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuxuan66.ecmc.cache.redis.RedisKit;
import com.yuxuan66.ecmc.common.utils.excel.ExcelHandler;
import com.yuxuan66.ecmc.common.utils.excel.annotation.ExcelColumn;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;


/**
 * 基础Service
 *
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
public class BaseService<T, M extends BaseMapper<T>> extends ServiceImpl<M, T> {

    @Resource
    protected RedisKit redis;


    /**
     * 根据注解处理要导出的数据
     *
     * @param data 数据
     * @return 处理后数据
     */
    protected List<Map<String, Object>> preDownload(List<T> data) {
        Class<T> entityClass = getEntityClass();
        // 过滤排序后的有效字段
        List<Field> fieldList = Stream.of(ReflectUtil.getFieldsDirectly(entityClass, true)).filter(item -> item.isAnnotationPresent(ExcelColumn.class)).sorted(Comparator.comparingInt(f -> f.getAnnotation(ExcelColumn.class).sort())).toList();
        List<Map<String, Object>> result = new ArrayList<>();
        for (T item : data) {
            Map<String, Object> map = new LinkedHashMap<>();

            for (Field field : fieldList) {
                ExcelColumn column = field.getAnnotation(ExcelColumn.class);

                Object fieldValue = ReflectUtil.getFieldValue(item, field);

                if (fieldValue == null) {
                    fieldValue = JSONPath.eval(item, field.getName() + "." + column.value());
                }
                if (column.handler() != ExcelHandler.class) {
                    ExcelHandler excelHandler = ReflectUtil.newInstance(column.handler());
                    fieldValue = excelHandler.convert(fieldValue, column);
                }
                map.put(column.name(), Convert.toStr(fieldValue, StrUtil.EMPTY));

            }
            result.add(map);
        }

        return result;
    }

}
