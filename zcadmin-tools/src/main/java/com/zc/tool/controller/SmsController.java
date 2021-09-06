package com.zc.tool.controller;

import com.zc.tool.service.SmsPost;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangC
 * @create 2021-08-26-17:53
 */
@RestController
@Configuration
@RequestMapping("/sms")
@Api(tags = "三方服务")
public class SmsController {
    String phone="17862905520";

    @Autowired
    SmsPost smsPost;

    @GetMapping("register")
    public void register(String phone){
        smsPost.smsToRegister(phone);
    }

    @GetMapping("login")
    public void login(String phone){
        smsPost.smsToRegister(phone);
    }
}
