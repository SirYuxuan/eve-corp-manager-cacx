package com.yuxuan66.ecmc.modules.api.service;

import cn.hutool.http.HttpUtil;
import org.springframework.stereotype.Service;

/**
 * 系统完全开发的API
 * @author Sir丶雨轩
 * @since 2022/12/9
 */
@Service
public class OpenApiService {


    /**
     * 获取每日一言的句子
     *
     * @return 每日一言
     */
    public String oneDay() {
        return HttpUtil.get("http://guozhivip.com/yy/api/api.php").replace("document.write(\"", "").replace("\");", "");
    }
}
