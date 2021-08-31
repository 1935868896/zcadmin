package com.zc.modules.security.controller;

import com.zc.annotation.*;
import com.zc.entity.ResultResponse;
import com.zc.jwt.JwtUtil;
import com.zc.modules.security.entity.User;
import com.zc.modules.system.mapper.SysUserMapper;
import com.zc.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    @Autowired
    SysUserMapper sysUserMapper;

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
    @Log("刷新token")
    @ResponseBody
    @PostMapping("/system/refresh/token")
    public ResultResponse refresh(HttpServletRequest request){
        //第一步验证密码的正确性
        log.info("刷新Token");
        String token=(String) request.getAttribute("X-Token");
        jwtUtil.canTokenBeRefreshed(token);
        String newToken = jwtUtil.refreshToken(token);
        log.info("全新的token:{}",newToken);
        return ResultResponse.success(newToken);
    }
    @Log("返回过期错误")
    @ResponseBody
    @RequestMapping("/error/expire")
    public ResultResponse expire(){
        log.info("给前端返回expire 接口");
        return new ResultResponse(1234,"token过期或者错误",null);
    }



    @Log("获取信息")
    @ResponseBody
    @GetMapping("/system/userinfo")
    public ResultResponse getInfo(){
        String username = SecurityUtils.getCurrentUsername();
        Set<String> rolesSet = sysUserMapper.selectRolesByUsername(username);


        Map<String,Object> map=new HashMap();
        map.put("roles",rolesSet);
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin");
        return ResultResponse.success(map);
    }


    @Log("登出")
    @ResponseBody
    @PostMapping("/user/logout")
    public ResultResponse logout(){
        /**
         * 此处处理登出逻辑
         */
        return ResultResponse.success("success");
    }
    @ResponseBody
    @GetMapping("/hello")
    @Log("测试")
    public String hello(){

        return "hello zcAdmin";
    }
}
