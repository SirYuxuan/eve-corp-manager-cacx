package com.yuxuan66.ecmc.modules.account.mapper;

import com.yuxuan66.ecmc.common.upload.entity.Attach;
import com.yuxuan66.ecmc.modules.account.entity.AccountSkill;
import com.yuxuan66.ecmc.support.base.BaseMapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/24
 */
public interface AccountSkillMapper extends BaseMapper<AccountSkill> {

    /**
     * 批量保存用户技能
     * @param list 技能列表
     * @return 插入条数
     */
    long batchInsert(List<AccountSkill> list);
}
