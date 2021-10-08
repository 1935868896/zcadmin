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

import com.zc.modules.system.entity.SysDictDetail;
import com.zc.modules.system.service.SysDictDetailService;
import com.zc.utils.SecurityUtils;
import cn.hutool.core.date.DateTime;


/**
 * 数据字典详情 信息操作处理
 *
 * @author zhangc
 * @date 2021-09-30
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/sysDictDetail")
@Api(tags = "数据字典详情信息管理")
public class SysDictDetailController {
    private final SysDictDetailService sysDictDetailService;

    @ApiOperationSupport(order = 2)
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("数据字典详情信息管理:根据参数获取对象集合")
    @PreAuthorize("@el.check('sysDictDetail:getListByParam')")
    public ResultResponse getListByParam(SysDictDetail record) {
        List<SysDictDetail> result = sysDictDetailService.selectListByParam(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }
    @ApiOperationSupport(order = 6)
    @ApiOperation("分页获得目标数据集合")
    @PostMapping("page")
    @Log("数据字典详情信息管理:根据参数获取分页对象集合")
    @PreAuthorize("@el.check('sysDictDetail:getPageByParam')")
    public ResultResponse getPageByParam(SysDictDetail record,@RequestBody Page page) {
        IPage<SysDictDetail> recordIPage = sysDictDetailService.selectPageByParam(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("数据字典详情信息管理:插入单条数据")
    @PreAuthorize("@el.check('sysDictDetail:insertOne')")
    public ResultResponse insertOne(@RequestBody SysDictDetail record) {
        if (record==null){
            throw new BadRequestException("插入数据为空");
        }
        record.setCreateBy(SecurityUtils.getCurrentUsername());
        record.setCreateTime(new DateTime());
        int result = sysDictDetailService.insertOne(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }
    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("数据字典详情信息管理:修改单条数据")
    @PreAuthorize("@el.check('sysDictDetail:updateById')")
    public ResultResponse updateById(@RequestBody SysDictDetail record) {
        if (record==null){
            throw new BadRequestException("修改数据为空");
        }
        record.setUpdateBy(SecurityUtils.getCurrentUsername());
        record.setUpdateTime(new DateTime());
        if (sysDictDetailService.updateById(record)) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }
    @ApiOperationSupport(order = 13)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("数据字典详情信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('sysDictDetail:deleteById')")
    public ResultResponse deleteById(Long id) {

        if (sysDictDetailService.deleteById(id)) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }
}
