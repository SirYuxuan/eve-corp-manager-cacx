package com.yuxuan66.ecmc.modules.account.mapper;

import com.yuxuan66.ecmc.modules.account.entity.AccountContract;
import com.yuxuan66.ecmc.modules.account.entity.AccountOrder;
import com.yuxuan66.ecmc.support.base.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/24
 */
public interface AccountContractMapper extends BaseMapper<AccountContract> {

    /**
     * 批量保存
     * @param list 数据
     * @return 插入条数
     */
    long batchInsert(List<AccountContract> list);


}
