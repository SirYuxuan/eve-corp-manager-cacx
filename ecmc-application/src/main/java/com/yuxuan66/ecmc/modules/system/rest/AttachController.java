package com.yuxuan66.ecmc.modules.system.rest;

import com.yuxuan66.ecmc.modules.system.service.AttachService;
import com.yuxuan66.ecmc.support.base.BaseController;
import com.yuxuan66.ecmc.support.base.resp.Rs;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Sir丶雨轩
 * @since 2022/12/13
 */
@RequestMapping(path = "/attach")
@RestController
public class AttachController extends BaseController<AttachService> {

    /**
     * 文件批量上传
     * @param files 文件列表
     * @return 上传后文件
     */
    @PostMapping(path = "/upload")
    public Rs upload(@RequestBody MultipartFile[] files){
        return Rs.ok(baseService.upload(files));
    }
}
