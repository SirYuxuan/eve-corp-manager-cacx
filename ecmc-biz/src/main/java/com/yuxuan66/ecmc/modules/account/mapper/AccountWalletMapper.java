package com.yuxuan66.ecmc.modules.account.mapper;

import com.yuxuan66.ecmc.modules.account.entity.AccountSkill;
import com.yuxuan66.ecmc.modules.account.entity.AccountWallet;
import com.yuxuan66.ecmc.support.base.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/24
 */
public interface AccountWalletMapper extends BaseMapper<AccountWallet> {
    /**
     * 批量保存用户钱包流水
     * @param list 技能列表
     * @return 插入条数
     */
    long batchInsert(List<AccountWallet> list);

    /**
     * 获取最后一条记录的ID
     * @param accountId 用户
     * @return ID
     */
    @Select("select * from corp_account_wallet where account_id = #{accountId} order by journal_id desc limit 1")
    AccountWallet getLastId(Long accountId);
}
