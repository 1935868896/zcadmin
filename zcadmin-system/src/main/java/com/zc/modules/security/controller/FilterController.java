package com.zc.modules.security.controller;

import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import com.zc.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangC
 * @create 2021-09-07-14:46
 */
@RestController
@ApiIgnore
@Slf4j
public class FilterController {
    @Autowired
    JwtUtil jwtUtil;

    @Log("返回过期错误")
    @RequestMapping("/error/expire")
    public ResultResponse expire() {
        log.info("给前端返回expire 接口");
        return new ResultResponse(1234, "token过期或者错误", null);
    }

    @Log("返回jwt错误")
    @RequestMapping("/error/jwt/black")
    public ResultResponse jwtBlack() {
        log.info("该jwt已经进入黑名单,无法登录");
        return new ResultResponse(50016,"jwt进入黑名单","jwt进入黑名单");
    }

    @Log("刷新token")
    @RequestMapping("/system/refresh/token")
    public ResultResponse refresh(HttpServletRequest request) {
        //第一步验证密码的正确性
        log.info("刷新Token");
        String token = (String) request.getAttribute("X-Token");
        if (jwtUtil.canTokenBeRefreshed(token)) {
            String newToken = jwtUtil.refreshToken(token);
            log.info("全新的token:{}", newToken);
            return ResultResponse.success(newToken);
        } else {
            return ResultResponse.error();
        }


    }
}
