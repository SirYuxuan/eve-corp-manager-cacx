package com.yuxuan66.ecmc.support.base.resp;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 接口分页数据统一
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
public class Ps extends Dict {


    /**
     * 状态码
     */
    public static final String TOTAL = "total";


    /**
     * 数据对象
     */
    public static final String DATA_TAG = "items";

    /**
     * 初始化一个新创建的 RespEntity 对象，使其表示一个空消息。
     */
    public Ps() {
    }

    /**
     * 初始化一个新创建的 Ps 对象
     *
     * @param total   条数
     * @param content 返回内容
     */
    public Ps(long total, List<?> content) {
        super.put("code",200);
        Map<String,Object> data = new LinkedHashMap<>();
        data.put(TOTAL, total);
        data.put(DATA_TAG, content);
        super.put("result",data);
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Ps ok(List<?> content) {
        return ok(content.size(), content);
    }
    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Ps ok(int count,List<?> content) {
        return new Ps(count, content);
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Ps ok(long count,List<?> content) {
        return new Ps(count, content);
    }
    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Ps ok(IPage<?> page) {
        return new Ps((int) page.getTotal(), page.getRecords());
    }
}
