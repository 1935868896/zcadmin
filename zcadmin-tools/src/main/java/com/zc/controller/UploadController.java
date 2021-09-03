package com.zc.controller;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.system.SystemUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author ZhangC
 * @create 2021-08-25-10:25
 */
@Controller
@Slf4j
@Configuration
@Api(tags = "三方服务")
public class UploadController {
    @Value("${upload.win.path}")
    String winPath;
    @Value("${upload.linux.path}")
    String linuxPath;


    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "fail";
        }
        String path="";
        if (SystemUtil.getOsInfo().isLinux()) {
            path=linuxPath;
        }else {
            path=winPath;
        }
log.info("name:" +file.getOriginalFilename());

        FileWriter writer = new FileWriter(path + File.separator+file.getName());
        log.info("文件存储位置: "+path +System.lineSeparator()+file.getName());
        try {
            writer.write(String.valueOf(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "123";
    }

}
