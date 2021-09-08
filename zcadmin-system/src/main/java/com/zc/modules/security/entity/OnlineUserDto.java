package com.zc.modules.security.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * @author ZhangC
 * @create 2021-09-08-15:46
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnlineUserDto {
    private String username;
    private String nickName;
    private String ip;
    private String address;
    private String browser;
    private String token;
    private String loginTime;

}
