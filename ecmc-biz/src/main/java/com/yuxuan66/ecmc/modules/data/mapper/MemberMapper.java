package com.yuxuan66.ecmc.modules.data.mapper;

import com.yuxuan66.ecmc.modules.account.entity.AccountSkill;
import com.yuxuan66.ecmc.modules.data.entity.Member;
import com.yuxuan66.ecmc.support.base.BaseMapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/12/24
 */
public interface MemberMapper extends BaseMapper<Member> {

    /**
     * 批量保存军团成员
     * @param list 成员列表
     * @return 插入条数
     */
    long batchInsert(List<Member> list);
}
