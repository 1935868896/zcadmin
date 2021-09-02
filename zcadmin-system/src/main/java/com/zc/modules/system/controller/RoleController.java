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
    import com.zc.modules.system.entity.Role;
import com.zc.modules.system.service.RoleService;

/**
 * 角色 信息操作处理
 *
 * @author zhangc
 * @date 2021-09-01
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/role")
@Api(tags = "角色信息管理")
public class RoleController {
    private final RoleService roleService;

    @ApiOperationSupport(order = 1)
    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("角色信息管理:根据主键获得对象数据")
    @PreAuthorize("@el.check('role:getRecordById')")
    public ResultResponse getRecordById(Long id) {
        Role result = roleService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 2)
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("角色信息管理:根据条件查询得到对象集合")
    @PreAuthorize("@el.check('role:getListByParam')")
    public ResultResponse getListByParam(Role record) {
        List<Role> result = roleService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 3)
    @GetMapping("/single")
    @ApiOperation("根据条件查询得到单个对象")
    @Log("角色信息管理:根据条件查询得到单个对象")
    @PreAuthorize("@el.check('role:getOneByParam')")
    public ResultResponse getOneByParam(Role record) {
        Role result = roleService.selectOneBySelective(record);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 4)
    @GetMapping("/ids")
    @ApiOperation("根据id集合获得目标数据集合")
    @Log("角色信息管理:根据id集合获得目标数据集合")
    @PreAuthorize("@el.check('role:getListByIds')")
    public ResultResponse getListByIds(@RequestParam(value = "ids", required = false) List<Long> ids) {
        List<Role> result = roleService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    @Log("角色信息管理:分页获得目标数据集合")
    @PreAuthorize("@el.check('role:getPageByParam')")
    public ResultResponse getPageByParam(Role record, Page page) {
        IPage<Role> recordIPage = roleService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperationSupport(order = 6)
    @Log("角色信息管理:根据条件查询符合数据的数量")
    @GetMapping("count")
    @ApiOperation("根据条件查询符合数据的数量")
    @PreAuthorize("@el.check('role:getCount')")
    public ResultResponse getCount(Role record) {
        int result = roleService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("角色信息管理:插入单条数据")
    @PreAuthorize("@el.check('role:insert')")
    public ResultResponse insert(@RequestBody Role record) {
        int result = roleService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 8)
    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    @Log("角色信息管理:批量插入数据")
    @PreAuthorize("@el.check('role:insertBatch')")
    public ResultResponse insertBatch(@RequestBody List<Role> records) {
        int result = roleService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("角色信息管理:修改数据")
    @PreAuthorize("@el.check('role:update')")
    public ResultResponse update(@RequestBody Role record) {
        int result = roleService.update(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 10)
    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    @Log("角色信息管理:修改部分数据")
    @PreAuthorize("@el.check('role:updateBySelective')")
    public ResultResponse updateSelective(@RequestBody Role record) {
        int result = roleService.updateSelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 11)
    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    @Log("角色信息管理:批量修改数据")
    @PreAuthorize("@el.check('role:updateBatch')")
    public ResultResponse updateBatch(@RequestBody List<Role> records) {
        int result = roleService.updateBatch(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    //如果某批数据中,有一个数据属性存在值,其他数据的属性不存在值,那么最终修改结果为其他数据的该属性将设为null值
    @ApiOperationSupport(order = 12)
    @ApiOperation("批量修改数据,仅修改部分属性")
    @PutMapping("batch/selective")
    @Log("角色信息管理:批量修改数据的部分属性")
    @PreAuthorize("@el.check('role:updateBatchBySelective')")
    public ResultResponse updateBatchBySelective(@RequestBody List<Role> records) {
        int result = roleService.updateBatchBySelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 13)
    @ApiOperation("根据条件删除数据")
    @DeleteMapping("bySelective")
    @Log("角色信息管理:根据条件删除数据")
    @PreAuthorize("@el.check('role:deleteBySelective')")
    public ResultResponse deleteBySelective(@RequestBody Role record) {
        int result = roleService.deleteBySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 14)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("角色信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('role:delete')")
    public ResultResponse delete(Long id) {
        int result = roleService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 15)
    @ApiOperation("根据主键集合批量删除数据")
    @DeleteMapping("ids")
    @Log("角色信息管理:根据主键集合批量删除数据")
    @PreAuthorize("@el.check('role:deleteByIds')")
    public ResultResponse deleteByIds(@RequestBody List<Long> ids) {
        int result = roleService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
