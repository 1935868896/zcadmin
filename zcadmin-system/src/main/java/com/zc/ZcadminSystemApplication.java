package com.zc;

import com.zc.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ZcadminSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZcadminSystemApplication.class, args);
    }
    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
