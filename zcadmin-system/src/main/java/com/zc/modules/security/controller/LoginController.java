package com.zc.modules.security.controller;

import com.zc.annotation.*;
import com.zc.entity.ResultResponse;
import com.zc.jwt.JwtUtil;
import com.zc.modules.security.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
    @PostMapping("/system/login")
    public ResultResponse login(@RequestBody User user){
        //第一步验证密码的正确性
        log.info("user信息:"+user.toString());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //第二步:生成jwt字符串,并返回
        String s = jwtUtil.JWTCreator(authentication);
        Map<String,Object> map=new HashMap();
        map.put("token",s);
        return ResultResponse.success(map);
    }





    @Log("登录")
    @ResponseBody
    @GetMapping("/system/userinfo")
    public ResultResponse getInfo(){
//  'admin-token': {
//        roles: ['admin'],
//        introduction: 'I am a super administrator',
//                avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
//                name: 'Super Admin'
//    },

        Map<String,Object> map=new HashMap();
        map.put("roles",new String[]{"admin"});
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin");
        return ResultResponse.success(map);
    }



    @ResponseBody
    @GetMapping("/hello")
    @Log("测试")
    public String hello(){

        return "hello zcAdmin";
    }
}
