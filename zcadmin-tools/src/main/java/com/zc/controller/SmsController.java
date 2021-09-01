package com.zc.controller;

import com.zc.Service.SmsPost;
import com.zc.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangC
 * @create 2021-08-26-17:53
 */
@RestController
@Configuration
@RequestMapping("/sms")
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
