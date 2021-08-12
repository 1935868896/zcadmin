package com.zc.generator.domain;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangC
 * @create 2021-08-11-16:30
 */
@Data
@Configuration
public class GenBaseInfo {
    @Value("gen.packageName")
    private String packageName ="com.zc.modules.quartz";
    @Value("gen.author")
    private String author="Zhang C";

    private String moduleName;

    private String basePackage;

    private String projectPath;

    private Boolean preservePrefix =false;




}
