package com.zc.controller;

import cn.hutool.core.convert.Convert;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.config.GlobalConfig;
import com.zc.entity.ResultResponse;
import com.zc.generator.domain.GenBaseInfo;
import com.zc.generator.domain.TableInfo;
import com.zc.generator.entity.CodeColumnConfig;
import com.zc.generator.service.CodeColumnConfigService;
import com.zc.generator.service.IGenService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 代码生成 操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/tool/gen")
@Api(tags = "代码生成")
public class GenController {

    private final IGenService genService;
    @Autowired
    CodeColumnConfigService codeColumnConfigService;

    @Autowired
    public GenController(IGenService genService) {
        this.genService = genService;
    }

    @GetMapping()
    public String gen() {
        String prefix = "tool/gen";
        return prefix + "/gen";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultResponse list(TableInfo tableInfo, Page page) {
        IPage<TableInfo> tableInfoIPage = genService.selectTablePage(tableInfo, page);
        return ResultResponse.success(tableInfoIPage);
    }

    /**
     * 生成代码
     */

    @GetMapping("/updateAndGenCode/{tableName}")
    public void genCode(HttpServletResponse response,HttpServletRequest request, @PathVariable("tableName") String tableName
    ,@RequestBody List<CodeColumnConfig> records) throws IOException {
        /**
         *
         */
        codeColumnConfigService.updateBatch(records);

        byte[] data = genService.generatorCode(tableName);
        //提供网页下载
        this.genCode(response,request, data, tableName);
    }

    @GetMapping("/genCode/{tableName}")
    public void genCode(HttpServletResponse response,HttpServletRequest request, @PathVariable("tableName") String tableName) throws IOException {
        /**
         *
         */
        byte[] data = genService.generatorCode(tableName);
        //提供网页下载
        this.genCode(response,request, data, tableName);
    }


    /**
     * 批量生成代码
     */

    @GetMapping("/batchGenCode")
    @ResponseBody
    public void batchGenCode(HttpServletResponse response,HttpServletRequest request ,String tables, GenBaseInfo genBaseInfo) throws IOException {
        handleGenBaseInfo(genBaseInfo);
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genService.generatorCode(tableNames);
        this.genCode(response, request,data);
    }

    private void genCode(HttpServletResponse response,HttpServletRequest request, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"zcadmin.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
        IOUtils.write(data, response.getOutputStream());
    }

    private void genCode(HttpServletResponse response, HttpServletRequest request,byte[] data, String tableName) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + tableName + ".zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
        IOUtils.write(data, response.getOutputStream());
    }
    private void handleGenBaseInfo(GenBaseInfo genBaseInfo){
        String packageName = genBaseInfo.getPackageName();

        if (genBaseInfo.getBasePackage() == null) {
            int lastIndex = packageName.lastIndexOf('.');
            genBaseInfo.setBasePackage(StrUtil.sub(packageName, 0, lastIndex));
        }
        if (genBaseInfo.getModuleName() == null) {
            int lastIndex = packageName.lastIndexOf('.');
            int nameLength = packageName.length();
            genBaseInfo.setModuleName(StrUtil.sub(packageName, lastIndex + 1, nameLength));
        }
        if (genBaseInfo.getProjectPath() == null) {
            StringBuilder projectPath = new StringBuilder();
            projectPath.append("main/java/");
            projectPath.append(packageName.replace(".", "/"));
            projectPath.append("/");
            genBaseInfo.setProjectPath(projectPath.toString());
        }
    }

}
