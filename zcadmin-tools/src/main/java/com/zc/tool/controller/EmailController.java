package com.zc.tool.controller;

import com.zc.tool.service.EmailPost;
import com.zc.entity.EmailEntity;
import com.zc.entity.ResultResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangC
 * @create 2021-08-27-10:55
 */
@RestController
@RequestMapping("/email")
@Api(tags = "三方服务")
public class EmailController {
    @Autowired
    EmailPost emailPost;
    @PostMapping
    public ResultResponse sendEmail(@RequestBody EmailEntity emailEntity){
        emailPost.sendAttachmentsMail(emailEntity);
        return ResultResponse.success();
    }

}
