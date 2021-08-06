package com.zc.controller;

import cn.hutool.core.convert.Convert;

import com.zc.generator.domain.TableInfo;
import com.zc.generator.service.IGenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import org.apache.commons.io.IOUtils;

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
    public void genCode(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genService.generatorCode(tableName);
        this.genCode(response, data);
    }

    /**
     * 批量生成代码
     */

    @GetMapping("/batchGenCode")
    @ResponseBody
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genService.generatorCode(tableNames);
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
