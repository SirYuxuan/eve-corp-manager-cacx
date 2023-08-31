package com.yuxuan66.ecmc.modules.utils.rest;

import com.yuxuan66.ecmc.modules.utils.entity.MessageBoard;
import com.yuxuan66.ecmc.modules.utils.service.MessageBoardService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sir丶雨轩
 * @since 2022/12/22
 */
@RestController
@RequestMapping(path = "/messageBoard")
public class MessageBoardController extends BaseController<MessageBoardService> {



    /**
     * 分页查询留言列表
     * @param baseQuery 查询条件
     * @return 留言列表
     */
    @GetMapping
    public Ps list(BaseQuery<MessageBoard> baseQuery){
        return baseService.list(baseQuery);
    }
    /**
     * 发布一个新的动态
     *
     * @param messageBoard 动态内容
     */
    @PostMapping
    public Rs add(@RequestBody MessageBoard messageBoard) {
        baseService.add(messageBoard);
        return Rs.ok();
    }
}
