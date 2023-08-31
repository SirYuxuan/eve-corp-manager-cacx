package com.yuxuan66.ecmc.modules.data.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.ecmc.common.utils.FileUtil;
import com.yuxuan66.ecmc.modules.data.entity.Member;
import com.yuxuan66.ecmc.modules.data.entity.query.MemberQuery;
import com.yuxuan66.ecmc.modules.data.mapper.MemberMapper;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import org.springframework.stereotype.Service;

/**
 * @author Sir丶雨轩
 * @since 2022/12/24
 */
@Service
public class MemberService extends BaseService<Member, MemberMapper> {

    /**
     * 获取查询条件
     * @param query 查询参数
     * @return 查询条件
     */
    private QueryWrapper<Member> getWrapper(MemberQuery query){
        query.processingBlurry("character_name", "nick_name");
        QueryWrapper<Member> wrapper = query.getQueryWrapper();
        wrapper.le(query.getDay() != null, "not_login_day", query.getDay());
        wrapper.eq(query.getCorpSystem()!=null,"corp_system",query.getCorpSystem());
        wrapper.eq(query.getSeatSystem()!=null,"seat_system",query.getSeatSystem());
        return wrapper;
    }

    /**
     * 分页查询军团成员列表
     * @param query 查询条件
     * @return 军团成员列表
     */
    public Ps list(MemberQuery query) {
        return Ps.ok(page(query.getPage(), getWrapper(query)));
    }

    /**
     * 导出军团成员列表
     * @param query 查询条件
     */
    public void download(MemberQuery query){
        FileUtil.exportExcel(preDownload(baseMapper.selectList(getWrapper(query))));
    }
}
