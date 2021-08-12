package com.zc.controller;

import cn.hutool.core.convert.Convert;

import cn.hutool.core.util.StrUtil;
import com.zc.config.GlobalConfig;
import com.zc.generator.domain.GenBaseInfo;
import com.zc.generator.service.IGenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public GenController(IGenService genService) {
        this.genService = genService;
    }

    @GetMapping()
    public String gen() {
        String prefix = "tool/gen";
        return prefix + "/gen";
    }

//    @PostMapping("/list")
//    @ResponseBody
//    public TableDataInfo list(TableInfo tableInfo) {
//        startPage();
//        List<TableInfo> list = genService.selectTableList(tableInfo);
//        return getDataTable(list);
//    }

    /**
     * 生成代码
     */

    @GetMapping("/genCode/{tableName}")
    public void genCode(HttpServletResponse response, @PathVariable("tableName") String tableName, GenBaseInfo genBaseInfo) throws IOException {
        //代码生成的主要实现
        String packageName= genBaseInfo.getPackageName();
        if (genBaseInfo.getBasePackage()==null){
            int lastIndex =packageName.lastIndexOf('.');
            genBaseInfo.setBasePackage(StrUtil.sub(packageName, 0, lastIndex));
        }
        if (genBaseInfo.getModuleName()==null){
            int lastIndex = packageName.lastIndexOf('.');
            int nameLength = packageName.length();
            genBaseInfo.setModuleName(StrUtil.sub(packageName, lastIndex + 1, nameLength));
        }
        if (genBaseInfo.getProjectPath()==null){
            StringBuilder projectPath = new StringBuilder();
            projectPath.append("main/java/");
            projectPath.append(packageName.replace("." , "/"));
            projectPath.append("/");
            genBaseInfo.setProjectPath(projectPath.toString());
        }


        byte[] data = genService.generatorCode(tableName, genBaseInfo);
        //提供网页下载
        this.genCode(response, data);
    }

    /**
     * 批量生成代码
     */

    @GetMapping("/batchGenCode")
    @ResponseBody
    public void batchGenCode(HttpServletResponse response, String tables,GenBaseInfo genBaseInfo) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genService.generatorCode(tableNames,genBaseInfo);
        this.genCode(response, data);
    }

    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
