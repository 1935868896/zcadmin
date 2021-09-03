package com.zc.controller;

import com.zc.Service.EmailPost;
import com.zc.entity.EmailEntity;
import com.zc.entity.ResultResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping
    public ResultResponse sendEmail(EmailEntity emailEntity){
        emailPost.sendMail(emailEntity);
        return ResultResponse.success();
    }

}
