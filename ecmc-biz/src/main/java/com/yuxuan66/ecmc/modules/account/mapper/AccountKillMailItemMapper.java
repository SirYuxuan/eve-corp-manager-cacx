package com.yuxuan66.ecmc.modules.account.mapper;

import com.yuxuan66.ecmc.modules.account.entity.AccountKillMailItem;
import com.yuxuan66.ecmc.support.base.BaseMapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/23
 */

public interface AccountKillMailItemMapper extends BaseMapper<AccountKillMailItem> {

    /**
     * 批量插入KM物品
     * @param list KM物品
     * @return 插入条数
     */
    long batchInsert(List<AccountKillMailItem> list);
}
