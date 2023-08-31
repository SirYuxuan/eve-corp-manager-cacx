package com.yuxuan66.ecmc.modules.data.rest;

import com.yuxuan66.ecmc.modules.data.entity.query.MemberQuery;
import com.yuxuan66.ecmc.modules.data.service.MemberService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sir丶雨轩
 * @since 2022/12/24
 */
@RestController
@RequestMapping(path = "/member")
public class MemberController extends BaseController<MemberService> {

    /**
     * 分页查询军团成员列表
     * @param query 查询条件
     * @return 军团成员列表
     */
    @GetMapping
    public Ps list(MemberQuery query) {
        return baseService.list(query);
    }

    /**
     * 导出军团成员列表
     * @param query 查询条件
     */
    @GetMapping(path = "/download")
    public void download(MemberQuery query){
        baseService.download(query);
    }
}
