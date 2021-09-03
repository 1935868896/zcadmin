package com.zc.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.annoation.Anonymous;
import com.zc.modules.system.vo.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import com.zc.modules.system.entity.Menu;
import com.zc.modules.system.service.MenuService;

/**
 * 系统菜单 信息操作处理
 *
 * @author zhangc
 * @date 2021-09-01
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/menu")
@Api(tags = "系统菜单信息管理")
public class MenuController {
    private final MenuService menuService;


    @ApiOperationSupport(order = 1)
    @GetMapping("/tree")
    @ApiOperation("获取树形的惨淡数据")
    @Log("系统菜单信息管理:获取树形的菜单数据")
    @Anonymous
//    @PreAuthorize("@el.check('menu:getTree')")
    public ResultResponse getTreeMenu() {
        List<MenuVO> result = menuService.selectMenuVO();
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 1)
    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("系统菜单信息管理:根据主键获得对象数据")
    @PreAuthorize("@el.check('menu:getRecordById')")
    public ResultResponse getRecordById(Long id) {
        Menu result = menuService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 3)
    @GetMapping("/single")
    @ApiOperation("根据条件查询得到单个对象")
    @Log("系统菜单信息管理:根据条件查询得到单个对象")
    @PreAuthorize("@el.check('menu:getOneByParam')")
    public ResultResponse getOneByParam(Menu record) {
        Menu result = menuService.selectOneBySelective(record);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("系统菜单信息管理:插入单条数据")
    @PreAuthorize("@el.check('menu:insert')")
    public ResultResponse insert(@RequestBody Menu record) {
        int result = menuService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("系统菜单信息管理:修改数据")
    @PreAuthorize("@el.check('menu:update')")
    public ResultResponse update(@RequestBody Menu record) {
        int result = menuService.update(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 13)
    @ApiOperation("根据条件删除数据")
    @DeleteMapping("bySelective")
    @Log("系统菜单信息管理:根据条件删除数据")
    @PreAuthorize("@el.check('menu:deleteBySelective')")
    public ResultResponse deleteBySelective(@RequestBody Menu record) {
        int result = menuService.deleteBySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 14)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("系统菜单信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('menu:delete')")
    public ResultResponse delete(Long id) {
        int result = menuService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
