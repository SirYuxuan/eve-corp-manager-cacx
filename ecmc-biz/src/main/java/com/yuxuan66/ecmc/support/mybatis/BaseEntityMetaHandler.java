package com.yuxuan66.ecmc.support.mybatis;

import cn.dev33.satoken.spring.SpringMVCUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yuxuan66.ecmc.common.utils.Lang;
import com.yuxuan66.ecmc.common.utils.StpUtil;
import com.yuxuan66.ecmc.modules.system.entity.User;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 自动追加基础数据
 *
 * @author Sir丶雨轩
 * @since 2022/12/10
 */
@Component
public class BaseEntityMetaHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Lang::getNowTimestamp, Timestamp.class);
        this.strictInsertFill(metaObject, "createBy", this::getNickName, String.class);
        this.strictInsertFill(metaObject, "createId", this::getId, Long.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime", Lang.getNowTimestamp());
        metaObject.setValue("updateBy", this.getNickName());
        metaObject.setValue("updateId", this.getId());
    }

    /**
     * 获取当前登录用户
     *
     * @return 用户
     */
    public User getUser() {
        User loginUser = null;
        if (SpringMVCUtil.isWeb() && StpUtil.isLogin()) {
            loginUser = StpUtil.getUser(User.class);
        }
        return loginUser;
    }

    public String getNickName() {
        return getUser() == null ? "系统管理员" : getUser().getNickName();
    }

    public Long getId() {
        return getUser() == null ? -1000L : getUser().getId();
    }


}
