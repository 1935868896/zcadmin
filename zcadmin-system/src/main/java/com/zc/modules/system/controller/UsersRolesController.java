package com.zc.modules.system.controller;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    import com.zc.modules.system.entity.RolesMenus;
    import com.zc.modules.system.vo.RolesMenusVO;
    import com.zc.modules.system.vo.UsersRolesVO;
    import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

    import java.util.ArrayList;
    import java.util.List;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
    import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
    import com.zc.modules.system.entity.UsersRoles;
import com.zc.modules.system.service.UsersRolesService;

/**
 * 用户角色关联 信息操作处理
 *
 * @author zhangc
 * @date 2021-09-01
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/usersRoles")
@Api(tags = "用户角色关联信息管理")
public class UsersRolesController {
    private final UsersRolesService usersRolesService;

    @ApiOperationSupport(order = 1)
    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("用户角色关联信息管理:根据主键获得对象数据")
    @PreAuthorize("@el.check('usersRoles:getRecordById')")
    public ResultResponse getRecordById(Long id) {
        UsersRoles result = usersRolesService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 2)
    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    @Log("用户角色关联信息管理:批量修改数据")
    @PreAuthorize("@el.check('usersRoles:updateBatch')")
    public ResultResponse updateBatch(@RequestBody UsersRolesVO usersRolesVO) {
        Long userId=usersRolesVO.getUserId();
        List<Long> rolesIds= usersRolesVO.getRoleIds();
        //1.删除
        usersRolesService.deleteBySelective(UsersRoles.builder().userId(userId).build());
        //2.新增
        List<UsersRoles> list=new ArrayList<>();
        if (rolesIds!=null&&rolesIds.size()>0){
            for (Long roleId : rolesIds) {
                UsersRoles build = UsersRoles.builder().roleId(roleId).userId(userId).build();
                list.add(build);
            }
            usersRolesService.insertBatch(list);
            return ResultResponse.success();

        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 2)
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("用户角色关联信息管理:根据条件查询得到对象集合")
    @PreAuthorize("@el.check('usersRoles:getListByParam')")
    public ResultResponse getListByParam(UsersRoles record) {
        List<UsersRoles> result = usersRolesService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 3)
    @GetMapping("/single")
    @ApiOperation("根据条件查询得到单个对象")
    @Log("用户角色关联信息管理:根据条件查询得到单个对象")
    @PreAuthorize("@el.check('usersRoles:getOneByParam')")
    public ResultResponse getOneByParam(UsersRoles record) {
        UsersRoles result = usersRolesService.selectOneBySelective(record);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 4)
    @GetMapping("/ids")
    @ApiOperation("根据id集合获得目标数据集合")
    @Log("用户角色关联信息管理:根据id集合获得目标数据集合")
    @PreAuthorize("@el.check('usersRoles:getListByIds')")
    public ResultResponse getListByIds(@RequestParam(value = "ids", required = false) List<Long> ids) {
        List<UsersRoles> result = usersRolesService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    @Log("用户角色关联信息管理:分页获得目标数据集合")
    @PreAuthorize("@el.check('usersRoles:getPageByParam')")
    public ResultResponse getPageByParam(UsersRoles record, Page page) {
        IPage<UsersRoles> recordIPage = usersRolesService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperationSupport(order = 6)
    @Log("用户角色关联信息管理:根据条件查询符合数据的数量")
    @GetMapping("count")
    @ApiOperation("根据条件查询符合数据的数量")
    @PreAuthorize("@el.check('usersRoles:getCount')")
    public ResultResponse getCount(UsersRoles record) {
        int result = usersRolesService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("用户角色关联信息管理:插入单条数据")
    @PreAuthorize("@el.check('usersRoles:insert')")
    public ResultResponse insert(@RequestBody UsersRoles record) {
        int result = usersRolesService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 8)
    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    @Log("用户角色关联信息管理:批量插入数据")
    @PreAuthorize("@el.check('usersRoles:insertBatch')")
    public ResultResponse insertBatch(@RequestBody List<UsersRoles> records) {
        int result = usersRolesService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("用户角色关联信息管理:修改数据")
    @PreAuthorize("@el.check('usersRoles:update')")
    public ResultResponse update(@RequestBody UsersRoles record) {
        int result = usersRolesService.update(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 10)
    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    @Log("用户角色关联信息管理:修改部分数据")
    @PreAuthorize("@el.check('usersRoles:updateBySelective')")
    public ResultResponse updateSelective(@RequestBody UsersRoles record) {
        int result = usersRolesService.updateSelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }



    //如果某批数据中,有一个数据属性存在值,其他数据的属性不存在值,那么最终修改结果为其他数据的该属性将设为null值
    @ApiOperationSupport(order = 12)
    @ApiOperation("批量修改数据,仅修改部分属性")
    @PutMapping("batch/selective")
    @Log("用户角色关联信息管理:批量修改数据的部分属性")
    @PreAuthorize("@el.check('usersRoles:updateBatchBySelective')")
    public ResultResponse updateBatchBySelective(@RequestBody List<UsersRoles> records) {
        int result = usersRolesService.updateBatchBySelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 13)
    @ApiOperation("根据条件删除数据")
    @DeleteMapping("bySelective")
    @Log("用户角色关联信息管理:根据条件删除数据")
    @PreAuthorize("@el.check('usersRoles:deleteBySelective')")
    public ResultResponse deleteBySelective(@RequestBody UsersRoles record) {
        int result = usersRolesService.deleteBySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 14)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("用户角色关联信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('usersRoles:delete')")
    public ResultResponse delete(Long id) {
        int result = usersRolesService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 15)
    @ApiOperation("根据主键集合批量删除数据")
    @DeleteMapping("ids")
    @Log("用户角色关联信息管理:根据主键集合批量删除数据")
    @PreAuthorize("@el.check('usersRoles:deleteByIds')")
    public ResultResponse deleteByIds(@RequestBody List<Long> ids) {
        int result = usersRolesService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
