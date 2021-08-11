package com.zc.modules.security.controller;

import com.zc.annotation.*;
import com.zc.jwt.JwtUtil;
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
    @Autowired
    JwtUtil jwtUtil;

    @Log("登录")
    @ResponseBody
    @GetMapping("/login")
    public String login(){
        //第一步验证密码的正确性
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("admin", "123456");
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //第二步:生成jwt字符串,并返回
        String s = jwtUtil.JWTCreator(authentication);
        return s;
    }

    @ResponseBody
    @GetMapping("/hello")
    @Log("测试")
    public String hello(){

        return "hello zcAdmin";
    }
}
