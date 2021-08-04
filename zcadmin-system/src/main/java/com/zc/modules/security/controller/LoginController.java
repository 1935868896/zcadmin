package com.zc.modules.security.controller;

import com.zc.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZhangC
 * @create 2021-08-02-17:23
 */
@Controller
@Slf4j
public class LoginController {
    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @Log("登录")
    @ResponseBody
    @GetMapping("/login")
    public String login(){
        //下面的步骤就是验证登录的过程,为简化流程,username和password这里固定了
        log.error("执行");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("admin", "123456");
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "login";
    }

    @ResponseBody
    @GetMapping("/hello")
    @Log("测试")
    public String hello(){

        return "hello zcAdmin";
    }
}
