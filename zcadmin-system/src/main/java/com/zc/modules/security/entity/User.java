package com.zc.modules.security.entity;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @author ZhangC
 * @create 2021-08-18-10:24
 */
@Data
public class User {
    @ApiParam(example = "admin")
    private String username;
    @ApiParam(example = "123456")
    private String password;
    @ApiParam(name = "验证方式", example = "password")
    private String verifyType;
    @ApiParam(name = "验证码")
    private String verifyCode;
    @ApiParam(name = "短信验证码")
    private String smsCode;
    @ApiParam(name = "验证码的uuid")
    private String uuid;
    @ApiParam(name = "手机号")
    private String phone;
}
