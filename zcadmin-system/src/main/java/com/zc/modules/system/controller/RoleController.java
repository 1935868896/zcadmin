package com.zc.modules.system.controller;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    import com.zc.modules.system.entity.RolesMenus;
    import com.zc.modules.system.service.RolesMenusService;
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
    private final RolesMenusService rolesMenusService;

    @ApiOperationSupport(order = 1)
    @GetMapping("/getMenuByRoleId")
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
    @ApiOperation("修改角色拥有的菜单权限")
    @PutMapping("batch")
    @Log("角色菜单关联信息管理:修改角色拥有的菜单权限")
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


}
