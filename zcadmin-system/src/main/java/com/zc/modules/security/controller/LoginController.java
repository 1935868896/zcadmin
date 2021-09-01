package com.zc.modules.security.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.core.util.IdUtil;
import com.zc.annoation.Anonymous;
import com.zc.annotation.*;
import com.zc.entity.ResultResponse;
import com.zc.exception.BadRequestException;
import com.zc.jwt.JwtUtil;
import com.zc.modules.security.entity.User;
import com.zc.modules.system.mapper.SysUserMapper;
import com.zc.utils.RedisUtil;
import com.zc.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangC
 * @create 2021-08-02-17:23
 */
@RestController
@Slf4j
public class LoginController {
    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    SysUserMapper sysUserMapper;

    @Log("登录")
    @PostMapping("/system/login")
    public ResultResponse login(@RequestBody User user, HttpServletRequest request) {
        //第一步 验证 验证码是否正确
        if (!"phoneVerify".equals(user.getVerifyType()) && !"thirdVerify".equals(user.getVerifyType())) {
//            request.setAttribute("verifyType","password");
            String uuid = user.getUuid();
            String code = null;
            if (uuid != null) {
                code = RedisUtil.StringOps.get(uuid);
                RedisUtil.KeyOps.delete(uuid);
            }
            if (uuid == null || user.getVerifyCode() == null || code == null) {
                throw new BadRequestException("验证码已经失效,请重新填写");
            }
            MathGenerator mathGenerator = new MathGenerator();
            boolean verify = mathGenerator.verify(code, user.getVerifyCode());
            if (!verify) {
                throw new BadRequestException("验证码填写错误,请重新填写");
            }

        }

        log.info("user信息:" + user.toString());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //第二步:生成jwt字符串,并返回
        String s = jwtUtil.JWTCreator(authentication);
        Map<String, Object> map = new HashMap();
        map.put("token", s);
        return ResultResponse.success(map);
    }

    @Log("刷新token")
    @GetMapping("/system/refresh/token")
    public ResultResponse refresh(HttpServletRequest request) {
        //第一步验证密码的正确性
        log.info("刷新Token");
        String token = (String) request.getAttribute("X-Token");
        jwtUtil.canTokenBeRefreshed(token);
        String newToken = jwtUtil.refreshToken(token);
        log.info("全新的token:{}", newToken);
        return ResultResponse.success(newToken);
    }

    @Log("返回过期错误")
    @GetMapping("/error/expire")
    public ResultResponse expire() {
        log.info("给前端返回expire 接口");
        return new ResultResponse(1234, "token过期或者错误", null);
    }


    @Log("获取信息")
    @GetMapping("/system/userinfo")
    public ResultResponse getInfo() {
        String username = SecurityUtils.getCurrentUsername();
        Set<String> rolesSet = sysUserMapper.selectRolesByUsername(username);


        Map<String, Object> map = new HashMap();
        map.put("roles", rolesSet);
        map.put("introduction", "I am a super administrator");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "Super Admin");
        return ResultResponse.success(map);
    }


    @Log("登出")
    @PostMapping("/user/logout")
    public ResultResponse logout() {
        /**
         * 此处处理登出逻辑
         */
        return ResultResponse.success("success");
    }

    @GetMapping("/hello")
    @Log("测试")
    public String hello() {

        return "hello zcAdmin";
    }


    @GetMapping("auth/code")
    @Anonymous
    public ResultResponse getCode() {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 45, 4, 4);
        // 自定义验证码内容为四则运算方式
        captcha.setGenerator(new MathGenerator());
        // 重新生成code
        captcha.createCode();

        String uuid = "code:key:" + IdUtil.simpleUUID();


        // 保存
        RedisUtil.StringOps.setEx(uuid, captcha.getCode(), 1, TimeUnit.MINUTES);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.getImageBase64Data());
            put("uuid", uuid);
        }};
        return ResultResponse.success(imgResult);
    }
}
