package com.yuxuan66.ecmc.support.base;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@Data
public class BaseQuery<T> {

    /**
     * 默认排序字段
     */
    private static final String DEFAULT_ORDER = "create_time";
    /**
     * 默认排序方式
     */
    private static final String DEFAULT_ORDER_BY = "desc";

    /**
     * 模糊查询字段
     */
    private String blurry;
    /**
     * 分页 页码
     */
    private long page;
    /**
     * 分页 每页大小
     */
    private long size;

    /**
     * 手动分页-开始条数
     */
    private long limitStart;

    /**
     * 禁止外部修改分页参数
     */
    private void setLimitStart(Long limitStart) {
    }

    /**
     * 排序字段
     */
    private String order;
    /**
     * 排序类型
     */
    private String orderBy;

    /**
     * 时间查询
     */
    private String[] createTime;

    public BaseQuery() {

    }

    private final QueryWrapper<T> queryWrapper = new QueryWrapper<>();

    public QueryWrapper<T> getQueryWrapper() {
        this.limitStart = (page) * size;
        this.processingSort();
        return queryWrapper;
    }

    /**
     * 获取MyBatis的分页对象
     *
     * @return 分页对象
     */
    public Page<T> getPage() {
        return new Page<T>(page, size);
    }

    /**
     * 获取MyBatis的分页对象携带排序参数
     *
     * @return 分页对象
     */
    public Page<T> getPageOrder() {
        Page<T> tPage = new Page<>(page, size);
        if (StrUtil.isNotBlank(order) && StrUtil.isNotBlank(orderBy)) {
            if (orderBy.contains(DEFAULT_ORDER_BY)) {
                tPage.addOrder(OrderItem.desc(StrUtil.toUnderlineCase(order)));
            } else {
                tPage.addOrder(OrderItem.asc(StrUtil.toUnderlineCase(order)));
            }
        } else {
            tPage.addOrder(OrderItem.desc(DEFAULT_ORDER));
        }
        return tPage;
    }

    /**
     * 处理分页参数
     */
    public void processingSort() {
        if (StrUtil.isNotBlank(order) && StrUtil.isNotBlank(orderBy)) {
            if (orderBy.contains(DEFAULT_ORDER_BY)) {
                queryWrapper.orderByDesc(StrUtil.toUnderlineCase(order));
            } else {
                queryWrapper.orderByAsc(StrUtil.toUnderlineCase(order));
            }
        } else {
            queryWrapper.orderByDesc(DEFAULT_ORDER);
        }
    }

    /**
     * 处理模糊查询数据
     *
     * @param params 字段
     */
    public void processingBlurry(String... params) {
        if (StrUtil.isNotBlank(this.getBlurry())) {
            queryWrapper.and(tQueryWrapper -> {
                for (int i = 0; i < params.length; i++) {
                    tQueryWrapper.like(params[i], blurry);
                    if (i != params.length - 1) {
                        tQueryWrapper.or();
                    }
                }
            });
        }
    }

    /**
     * 根据指定时间字段查询
     *
     * @param field 字段
     */
    public void processingCreateTime(String field) {
        // 数据正常的长度
        int checkOkLen = 2;
        if (this.getCreateTime() != null && this.getCreateTime().length == checkOkLen) {
            queryWrapper.ge(field, this.getCreateTime()[0]);
            queryWrapper.le(field, this.getCreateTime()[1]);
        }
    }

    /**
     * 处理创建时间
     */
    public void processingCreateTime() {
        processingCreateTime("create_time");
    }

}
