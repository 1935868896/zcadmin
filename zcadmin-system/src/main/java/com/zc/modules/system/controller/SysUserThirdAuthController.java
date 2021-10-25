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

import com.zc.modules.system.entity.SysUserThirdAuth;
import com.zc.modules.system.service.SysUserThirdAuthService;


/**
 * 第三方认证用户 信息操作处理
 *
 * @author zhangc
 * @date 2021-10-14
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/sysUserThirdAuth")
@Api(tags = "第三方认证用户信息管理")
public class SysUserThirdAuthController {
    private final SysUserThirdAuthService sysUserThirdAuthService;

    @ApiOperationSupport(order = 3)
    @GetMapping("/single")
    @ApiOperation("根据条件查询得到单个对象")
    @Log("第三方认证用户信息管理:根据参数获取单条数据")
    @PreAuthorize("@el.check('sysUserThirdAuth:getOneByParam')")
    public ResultResponse getOneByParam(SysUserThirdAuth record) {
        SysUserThirdAuth result = sysUserThirdAuthService.selectOneByParam(record);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }
    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("第三方认证用户信息管理:插入单条数据")
    @PreAuthorize("@el.check('sysUserThirdAuth:insertOne')")
    public ResultResponse insertOne(@RequestBody SysUserThirdAuth record) {
        if (record==null){
            throw new BadRequestException("插入数据为空");
        }
        int result = sysUserThirdAuthService.insertOne(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }
    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("第三方认证用户信息管理:修改单条数据")
    @PreAuthorize("@el.check('sysUserThirdAuth:updateById')")
    public ResultResponse updateById(@RequestBody SysUserThirdAuth record) {
        if (record==null){
            throw new BadRequestException("修改数据为空");
        }
        if (sysUserThirdAuthService.updateById(record)) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }
    @ApiOperationSupport(order = 13)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("第三方认证用户信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('sysUserThirdAuth:deleteById')")
    public ResultResponse deleteById(Long id) {

        if (sysUserThirdAuthService.deleteById(id)) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }
}
