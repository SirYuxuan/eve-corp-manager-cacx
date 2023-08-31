package com.yuxuan66.ecmc.modules.utils.service;

import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import com.yuxuan66.ecmc.modules.account.service.UserAccountService;
import com.yuxuan66.ecmc.modules.utils.entity.MessageBoard;
import com.yuxuan66.ecmc.modules.utils.mapper.MessageBoardMapper;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import com.yuxuan66.ecmc.support.base.BaseService;
import com.yuxuan66.ecmc.support.base.resp.Ps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Sir丶雨轩
 * @since 2022/12/22
 */
@Service
@RequiredArgsConstructor
public class MessageBoardService extends BaseService<MessageBoard, MessageBoardMapper> {

    private final UserAccountService userAccountService;

    /**
     * 发布一个新的留言
     * @param messageBoard 留言内容
     */
    public void add(MessageBoard messageBoard){
        UserAccount mainAccount = userAccountService.getMainAccount();
        messageBoard.setAccountId(mainAccount.getId());
        messageBoard.setLikes(0);
        messageBoard.setCharacterId(mainAccount.getCharacterId());
        messageBoard.setCharacterName(mainAccount.getCharacterName());
        messageBoard.insert();
    }

    /**
     * 分页查询留言列表
     * @param baseQuery 查询条件
     * @return 留言列表
     */
    public Ps list(BaseQuery<MessageBoard> baseQuery){
        return Ps.ok(page(baseQuery.getPage(),baseQuery.getQueryWrapper()));
    }

}

