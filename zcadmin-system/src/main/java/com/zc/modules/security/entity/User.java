package com.zc.modules.security.entity;

import io.swagger.annotations.ApiModel;
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
}
