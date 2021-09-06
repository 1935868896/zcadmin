package com.zc.tool.controller;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.system.SystemUtil;
import com.zc.entity.ResultResponse;
import com.zc.tool.entity.LocalStorage;
import com.zc.tool.service.LocalStorageService;
import com.zc.utils.HandleFileUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author ZhangC
 * @create 2021-08-25-10:25
 */
@RestController
@Slf4j
@Configuration
@Api(tags = "三方服务")
@RequiredArgsConstructor
public class UploadController {

    @Value("${upload.win.path}")
    String winPath;
    @Value("${upload.linux.path}")
    String linuxPath;

    private final LocalStorageService localStorageService;

    @PostMapping("/upload")
    public ResultResponse upload(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        /**
         *  首先需要判断是否存在重名的
         */
        int i = localStorageService.selectCountBySelective(LocalStorage.builder().name(fileName).build());
        if (i > 0) {
            return ResultResponse.error("文件名称重复");
        }
        if (file.isEmpty()) {
            ResultResponse.error("上传文件失败");
        }


        String path = "";
        if (SystemUtil.getOsInfo().isLinux()) {
            path = linuxPath;
        } else {
            path = winPath;
        }
        String realName = file.getOriginalFilename();
        log.info("name:" + realName);

        // 获取后缀 获取名字 获取文件
        String suffix = "";
        int j = realName.lastIndexOf(".");
        if (j >= 0) {
            suffix = realName.substring(j+1, realName.length());
        }
        String size = HandleFileUtil.getPrintSize(file.getSize());
        String storagePath = path + File.separator + file.getOriginalFilename();
        String type = "";

        try {
            type = FileTypeUtil.getType(new ByteArrayInputStream(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File dest = new File(storagePath);

        log.info("文件存储位置: " + storagePath);
        try {
            file.transferTo(dest);
            log.info("文件名: {},真实名称: {} ,文件大小:{} ,文件路径:{} ,文件后缀:{},文件类型{}",
                    file, realName, size, storagePath, suffix, type
            );
            LocalStorage build = LocalStorage.builder()
                    .name(fileName).realName(realName).fileSize(size).path(storagePath).suffix(suffix).type(type)
                    .build();
            localStorageService.insert(build);

        } catch (IOException e) {
            e.printStackTrace();
            return ResultResponse.error("上传文件失败");
        }
        return ResultResponse.success();
    }

    /**
     * @param id     想要下载的文件的id
     * @param response
     * @功能描述 下载文件:
     */
    @GetMapping("/third/download/{id}")
    public void download(@PathVariable("id") Long id, HttpServletResponse response) {
        LocalStorage localStorage = localStorageService.selectByPrimaryKey(id);
        String path=localStorage.getPath();
        try {
            // path是指想要下载的文件的路径
            File file = new File(path);
            log.info(file.getPath());
            // 获取文件名
            String filename = file.getName();
            // 获取文件后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            log.info("文件后缀名：" + ext);

            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
