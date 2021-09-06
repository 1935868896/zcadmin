package com.zc.tool.entity;

import lombok.Data;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangC
 * @create 2021-08-31-14:00
 */
@Data
@Component
@ConfigurationProperties(prefix = "sms.config")
public class SmsConfig {


    String host;
    String path;
    String method ;
    String appcode;
    String smsSignId;
    String templateId;

}
