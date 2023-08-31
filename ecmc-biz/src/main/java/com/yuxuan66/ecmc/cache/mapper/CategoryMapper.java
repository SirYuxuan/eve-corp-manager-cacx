package com.yuxuan66.ecmc.cache.mapper;

import com.yuxuan66.ecmc.cache.entity.Category;
import com.yuxuan66.ecmc.support.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2021/12/15
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 批量插入eve Category数据
     * @param categoryList Category集合
     * @return 插入条数
     */
    long batchInsert(List<Category> categoryList);
}
