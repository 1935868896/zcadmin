package com.zc.generator.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.zc.annoation.Anonymous;
import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import com.zc.generator.entity.CodeColumnConfig;
import com.zc.generator.entity.CodeGenConfig;
import com.zc.generator.service.CodeColumnConfigService;
import com.zc.generator.service.CodeGenConfigService;
import com.zc.generator.service.IGenService;
import com.zc.generator.vo.GenConfigVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangC
 * @create 2021-08-24-16:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gen/genConfig")
@Api(tags = "代码生成配置管理")
public class GenConfigController {
    private final CodeColumnConfigService codeColumnConfigService;
    private final CodeGenConfigService codeGenConfigService;
    private final IGenService genService;


    @ApiOperationSupport(order = 1)
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("代码生成字段存储信息管理:根据条件查询得到对象集合")
//    @PreAuthorize("@el.check('codeColumnConfig:getListByParam')")
    @Anonymous
    public ResultResponse getListByName(String tableName) {

        CodeGenConfig codeGenConfig = codeGenConfigService.selectOneBySelective(CodeGenConfig.builder().tableName(tableName).build());
        List<CodeColumnConfig> codeColumnConfigs = new ArrayList<>();
        /**
         * 获取 表配置 和列配置对象 如果不存在的话就初始化赋值创建
         */
        GenConfigVO result;
        if (codeGenConfig == null) {
            result = genService.initGenConfig(codeGenConfig, codeColumnConfigs, tableName);
        } else {
            codeColumnConfigs = codeColumnConfigService.selectListBySelective(CodeColumnConfig.builder().tableName(tableName).build());
            result = GenConfigVO.builder().codeGenConfig(codeGenConfig).columnConfigList(codeColumnConfigs).build();
        }

        return ResultResponse.success(result);
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation("修改代码生成表设置")
    @PutMapping("codeGenConfig")
    @Log("代码生成表设置:修改数据")
    @Anonymous
    public ResultResponse update(@RequestBody CodeGenConfig record) {
        int result = codeGenConfigService.update(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 3)
    @ApiOperation("批量修改数据")
    @PutMapping("codeColumnConfig")
    @Log("代码生成字段存储信息管理:批量修改数据")
//    @PreAuthorize("@el.check('codeColumnConfig:updateBatch')")
    @Anonymous
    public ResultResponse updateBatch(@RequestBody List<CodeColumnConfig> records) {
        int result = codeColumnConfigService.updateBatch(records);
        return ResultResponse.success();
    }


    @ApiOperationSupport(order = 4)
    @GetMapping("sync/{tableName}")
    @ApiOperation("根据条件查询得到对象集合")
    @Log("代码生成字段存储信息管理:根据条件查询得到对象集合")
//    @PreAuthorize("@el.check('codeColumnConfig:getListByParam')")
    @Anonymous
    public ResultResponse getSyncByName(@PathVariable String tableName) {
        genService.syncColumnConfig(tableName);
        return ResultResponse.success();
    }

}
