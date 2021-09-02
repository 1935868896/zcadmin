package com.zc.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.modules.system.vo.RolesMenusVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import com.zc.modules.system.entity.RolesMenus;
import com.zc.modules.system.service.RolesMenusService;

/**
 * 角色菜单关联 信息操作处理
 *
 * @author zhangc
 * @date 2021-09-01
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/rolesMenus")
@Api(tags = "角色菜单关联信息管理")
public class RolesMenusController {
    private final RolesMenusService rolesMenusService;

    @ApiOperationSupport(order = 1)
    @GetMapping("/roleId")
    @ApiOperation("根据角色id获取菜单id")
    @Log("角色菜单关联信息管理:根据角色id获取菜单id的集合")
    @PreAuthorize("@el.check('rolesMenus:getMenuByRoleId')")
    public ResultResponse getMenuByRoleId(Long id) {

        List<RolesMenus> rolesMenus = rolesMenusService.selectListBySelective(RolesMenus.builder().roleId(id).build());
        if (rolesMenus != null) {
            List<Long> collect = rolesMenus.stream().map(RolesMenus::getMenuId).collect(Collectors.toList());
            return ResultResponse.success(collect);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 2)
    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    @Log("角色菜单关联信息管理:批量修改数据")
    @PreAuthorize("@el.check('rolesMenus:updateBatch')")
    public ResultResponse updateBatch(@RequestBody RolesMenusVO rolesMenusVO) {
        Long roleId=rolesMenusVO.getRoleId();
        List<Long> menuIds= rolesMenusVO.getMenuIds();
        //1.删除
        rolesMenusService.deleteBySelective(RolesMenus.builder().roleId(roleId).build());
        //2.新增
        List<RolesMenus> list=new ArrayList<>();
        if (menuIds!=null&&menuIds.size()>0){
            for (Long menuId : menuIds) {
                RolesMenus build = RolesMenus.builder().roleId(roleId).menuId(menuId).build();
                list.add(build);
            }
            rolesMenusService.insertBatch(list);
            return ResultResponse.success();

        }
        return ResultResponse.error();
    }







    @ApiOperationSupport(order = 1)
    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("角色菜单关联信息管理:根据主键获得对象数据")
    @PreAuthorize("@el.check('rolesMenus:getRecordById')")
    public ResultResponse getRecordById(Long id) {
        RolesMenus result = rolesMenusService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }




    @ApiOperationSupport(order = 2)
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("角色菜单关联信息管理:根据条件查询得到对象集合")
    @PreAuthorize("@el.check('rolesMenus:getListByParam')")
    public ResultResponse getListByParam(RolesMenus record) {
        List<RolesMenus> result = rolesMenusService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 3)
    @GetMapping("/single")
    @ApiOperation("根据条件查询得到单个对象")
    @Log("角色菜单关联信息管理:根据条件查询得到单个对象")
    @PreAuthorize("@el.check('rolesMenus:getOneByParam')")
    public ResultResponse getOneByParam(RolesMenus record) {
        RolesMenus result = rolesMenusService.selectOneBySelective(record);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 4)
    @GetMapping("/ids")
    @ApiOperation("根据id集合获得目标数据集合")
    @Log("角色菜单关联信息管理:根据id集合获得目标数据集合")
    @PreAuthorize("@el.check('rolesMenus:getListByIds')")
    public ResultResponse getListByIds(@RequestParam(value = "ids", required = false) List<Long> ids) {
        List<RolesMenus> result = rolesMenusService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    @Log("角色菜单关联信息管理:分页获得目标数据集合")
    @PreAuthorize("@el.check('rolesMenus:getPageByParam')")
    public ResultResponse getPageByParam(RolesMenus record, Page page) {
        IPage<RolesMenus> recordIPage = rolesMenusService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperationSupport(order = 6)
    @Log("角色菜单关联信息管理:根据条件查询符合数据的数量")
    @GetMapping("count")
    @ApiOperation("根据条件查询符合数据的数量")
    @PreAuthorize("@el.check('rolesMenus:getCount')")
    public ResultResponse getCount(RolesMenus record) {
        int result = rolesMenusService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("角色菜单关联信息管理:插入单条数据")
    @PreAuthorize("@el.check('rolesMenus:insert')")
    public ResultResponse insert(@RequestBody RolesMenus record) {
        int result = rolesMenusService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 8)
    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    @Log("角色菜单关联信息管理:批量插入数据")
    @PreAuthorize("@el.check('rolesMenus:insertBatch')")
    public ResultResponse insertBatch(@RequestBody List<RolesMenus> records) {
        int result = rolesMenusService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("角色菜单关联信息管理:修改数据")
    @PreAuthorize("@el.check('rolesMenus:update')")
    public ResultResponse update(@RequestBody RolesMenus record) {
        int result = rolesMenusService.update(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 10)
    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    @Log("角色菜单关联信息管理:修改部分数据")
    @PreAuthorize("@el.check('rolesMenus:updateBySelective')")
    public ResultResponse updateSelective(@RequestBody RolesMenus record) {
        int result = rolesMenusService.updateSelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }




    //如果某批数据中,有一个数据属性存在值,其他数据的属性不存在值,那么最终修改结果为其他数据的该属性将设为null值
    @ApiOperationSupport(order = 12)
    @ApiOperation("批量修改数据,仅修改部分属性")
    @PutMapping("batch/selective")
    @Log("角色菜单关联信息管理:批量修改数据的部分属性")
    @PreAuthorize("@el.check('rolesMenus:updateBatchBySelective')")
    public ResultResponse updateBatchBySelective(@RequestBody List<RolesMenus> records) {
        int result = rolesMenusService.updateBatchBySelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 13)
    @ApiOperation("根据条件删除数据")
    @DeleteMapping("bySelective")
    @Log("角色菜单关联信息管理:根据条件删除数据")
    @PreAuthorize("@el.check('rolesMenus:deleteBySelective')")
    public ResultResponse deleteBySelective(@RequestBody RolesMenus record) {
        int result = rolesMenusService.deleteBySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 14)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("角色菜单关联信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('rolesMenus:delete')")
    public ResultResponse delete(Long id) {
        int result = rolesMenusService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 15)
    @ApiOperation("根据主键集合批量删除数据")
    @DeleteMapping("ids")
    @Log("角色菜单关联信息管理:根据主键集合批量删除数据")
    @PreAuthorize("@el.check('rolesMenus:deleteByIds')")
    public ResultResponse deleteByIds(@RequestBody List<Long> ids) {
        int result = rolesMenusService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
