package com.zc.tool.util;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.system.SystemUtil;
import com.zc.tool.entity.LocalStorage;
import com.zc.utils.HandleFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author ZhangC
 * @create 2021-09-08-11:00
 */
@Component
@Slf4j
public class MultipartFileHandle {
    @Value("${upload.win.path}")
    String winPath;
    @Value("${upload.linux.path}")
    String linuxPath;

    public LocalStorage getLocalStorage(MultipartFile file,String fileName){
        String path = "";
        if (SystemUtil.getOsInfo().isLinux()) {
            path = linuxPath;
        } else {
            path = winPath;
        }

        String realName = file.getOriginalFilename();

        // 获取后缀 获取名字 获取文件
        String suffix = "";
        String fileNameNotSuffix="";
        int j = realName.lastIndexOf(".");
        if (j >= 0) {
            suffix = realName.substring(j+1, realName.length());
            fileNameNotSuffix=realName.substring(0,j);
        }
        realName=fileNameNotSuffix+"-"+IdUtil.simpleUUID()+"."+suffix;
        String size = HandleFileUtil.getPrintSize(file.getSize());
        String storagePath = path + File.separator + realName;
        String type = "";
        try {
            type = FileTypeUtil.getType(new ByteArrayInputStream(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalStorage build = LocalStorage.builder()
                .name(fileName).realName(realName).fileSize(size).path(storagePath).suffix(suffix).type(type)
                .build();
        return build;
    }
}
