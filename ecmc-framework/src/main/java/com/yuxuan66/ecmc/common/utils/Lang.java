package com.yuxuan66.ecmc.common.utils;

import cn.hutool.core.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * 系统通用工具类
 *
 * @author Sir丶雨轩
 * @since 2022/12/10
 */
public final class Lang {

    /**
     * 获取当前时间戳对象
     *
     * @return 时间戳对象
     */
    public static Timestamp getNowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取指定类的指定索引泛型类型
     *
     * @param clazz 类
     * @param index 索引
     * @return 泛型
     */
    public static Type getGenericType(Class<?> clazz, int index) {
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        return parameterizedType.getActualTypeArguments()[index];
    }

    /**
     * 转换offsetDateTime to Timestamp
     *
     * @param offsetDateTime offsetDateTime
     * @return Timestamp
     */
    public static Timestamp get(OffsetDateTime offsetDateTime) {
        if(offsetDateTime == null){
            return null;
        }
        return Timestamp.from(offsetDateTime.toLocalDateTime().toInstant(ZoneOffset.ofHours(0)));
    }

    /**
     * 格式化数字显示
     * @param number 数字
     * @return 格式化结果
     */
    public static String getFormatNumber(Number number){
        return NumberUtil.decimalFormat(",###", (number.doubleValue() ));
    }

    /**
     * map转url形式
     * @param params 参数
     * @return url
     */
    public static String buildQuery(Map<String, Object> params) {
        String[] query = new String[params.size()];
        int index = 0;
        for (String key : params.keySet()) {
            String val = String.valueOf(params.get(key) != null ? params.get(key) : "");
            val = URLEncoder.encode(val, StandardCharsets.UTF_8);
            query[index++] = key+"="+val;
        }

        return StringUtils.join(query, "&");
    }
}
