package com.zc.modules.system.controller;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import com.zc.exception.BadRequestException;

import com.zc.modules.system.entity.SysDict;
import com.zc.modules.system.service.SysDictService;
import com.zc.utils.SecurityUtils;
import cn.hutool.core.date.DateTime;


/**
 * 数据字典 信息操作处理
 *
 * @author zhangc
 * @date 2021-09-30
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/sysDict")
@Api(tags = "数据字典信息管理")
public class SysDictController {
    private final SysDictService sysDictService;

    @ApiOperationSupport(order = 1)
    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("数据字典信息管理:根据主键获取对象")
    @PreAuthorize("@el.check('sysDict:getObjectById')")
    public ResultResponse getObjectById(Long id) {
        SysDict result = sysDictService.selectObjectById(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }
    @ApiOperationSupport(order = 3)
    @GetMapping("/single")
    @ApiOperation("根据条件查询得到单个对象")
    @Log("数据字典信息管理:根据参数获取单条数据")
    @PreAuthorize("@el.check('sysDict:getOneByParam')")
    public ResultResponse getOneByParam(SysDict record) {
        SysDict result = sysDictService.selectOneByParam(record);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }
    @ApiOperationSupport(order = 6)
    @ApiOperation("分页获得目标数据集合")
    @PostMapping("page")
    @Log("数据字典信息管理:根据参数获取分页对象集合")
    @PreAuthorize("@el.check('sysDict:getPageByParam')")
    public ResultResponse getPageByParam(SysDict record,@RequestBody Page page) {
        IPage<SysDict> recordIPage = sysDictService.selectPageByParam(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("数据字典信息管理:插入单条数据")
    @PreAuthorize("@el.check('sysDict:insertOne')")
    public ResultResponse insertOne(@RequestBody SysDict record) {
        if (record==null){
            throw new BadRequestException("插入数据为空");
        }
        record.setCreateBy(SecurityUtils.getCurrentUsername());
        record.setCreateTime(new DateTime());
        int result = sysDictService.insertOne(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }
    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("数据字典信息管理:修改单条数据")
    @PreAuthorize("@el.check('sysDict:updateById')")
    public ResultResponse updateById(@RequestBody SysDict record) {
        if (record==null){
            throw new BadRequestException("修改数据为空");
        }
        record.setUpdateBy(SecurityUtils.getCurrentUsername());
        record.setUpdateTime(new DateTime());
        if (sysDictService.updateById(record)) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }
    @ApiOperationSupport(order = 13)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("数据字典信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('sysDict:deleteById')")
    public ResultResponse deleteById(Long id) {
        int result = sysDictService.deleteById(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }
}
