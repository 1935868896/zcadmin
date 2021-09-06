package com.zc.tool.service;

import com.zc.tool.entity.SmsConfig;
import com.zc.utils.CodeUtil;
import com.zc.utils.RedisUtil;
import com.zc.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangC
 * @create 2021-08-31-13:59
 */
@Component
@Slf4j
public class SmsPost {
    @Autowired
    RedisUtil redisUtil;



    public void sendSms(String phone,String verifyCode) throws Exception {

        //获取参数
        SmsConfig properties = SpringContextHolder.getBean(SmsConfig.class);

        String host = properties.getHost();
        String path = properties.getPath();
        String method = properties.getMethod();
        String appcode = properties.getAppcode();
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phone);
        querys.put("param", "**code**:"+verifyCode+",**minute**:5");
        querys.put("smsSignId", properties.getSmsSignId());
        querys.put("templateId", properties.getTemplateId());
        Map<String, String> bodys = new HashMap<String, String>();

            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            // 这里不再发送短信,需要的时候去掉注释即可
//            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);

            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));

    }

    public void smsToRegister(String phone){
        // 生成4位验证码
        String verifyCode = CodeUtil.generateVerifyCode(4);
        try {
            sendSms(phone,verifyCode);
            log.info("将注册验证码 {} 发送到手机号: {},且将键值对存入redis",verifyCode,phone);
            RedisUtil.StringOps.setEx("sms:register:"+phone,verifyCode,5, TimeUnit.MINUTES);
        }catch (Exception e){
            e.printStackTrace();
            log.error("短信服务调用失败: 将验证码 {} 发送到手机号: {}",verifyCode,phone);
        }
    }
    public void smsToLogin(String phone){
        // 生成4位验证码
        String verifyCode = CodeUtil.generateVerifyCode(4);
        try {
            sendSms(phone,verifyCode);
            log.info("将登录验证码 {} 发送到手机号: {},且将键值对存入redis",verifyCode,phone);
            RedisUtil.StringOps.setEx("sms:login:"+phone,verifyCode,5, TimeUnit.MINUTES);
        }catch (Exception e){
            e.printStackTrace();
            log.error("短信服务调用失败: 将验证码 {} 发送到手机号: {}",verifyCode,phone);
        }
    }

}
