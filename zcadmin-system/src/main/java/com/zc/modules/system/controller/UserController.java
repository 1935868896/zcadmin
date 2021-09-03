package com.zc.modules.system.controller;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    import com.zc.modules.system.entity.UsersRoles;
    import com.zc.modules.system.service.UsersRolesService;
    import com.zc.modules.system.vo.UserVO;
    import com.zc.modules.system.vo.UsersRolesVO;
    import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Set;

    import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
    import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
    import com.zc.modules.system.entity.User;
import com.zc.modules.system.service.UserService;

/**
 * 系统用户 信息操作处理
 *
 * @author zhangc
 * @date 2021-09-01
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/user")
@Api(tags = "系统用户信息管理")
public class UserController {
    private final UserService userService;
    private final UsersRolesService usersRolesService;


    @ApiOperationSupport(order = 1)
    @ApiOperation("更新用户数据以及拥有的角色")
    @PutMapping
    @Log("系统用户信息管理:更新用户数据以及拥有的角色")
    @PreAuthorize("@el.check('user:update')")
    public ResultResponse update(@RequestBody UserVO record) {
        User user=record.getUser();
        Set<Long> roles = record.getRoles();
        List<UsersRoles> usersRolesList=new ArrayList<>();
        if (roles!=null&&roles.size()>0&&user!=null){
            for (Long roleId : roles) {
                UsersRoles usersRoles=new UsersRoles();
                usersRoles.setUserId(user.getUserId());
                usersRoles.setRoleId(roleId);
                usersRolesList.add(usersRoles);
            }
            usersRolesService.deleteBySelective(UsersRoles.builder().userId(user.getUserId()).build());
            usersRolesService.insertBatch(usersRolesList);
        }

        int result = userService.update(record.getUser());
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 1)
    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("系统用户信息管理:根据主键获得对象数据")
    @PreAuthorize("@el.check('user:getRecordById')")
    public ResultResponse getRecordById(Long id) {
        UserVO result = userService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 2)
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("系统用户信息管理:根据条件查询得到对象集合")
    @PreAuthorize("@el.check('user:getListByParam')")
    public ResultResponse getListByParam(User record) {
        List<User> result = userService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }



    @ApiOperationSupport(order = 5)
    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    @Log("系统用户信息管理:分页获得目标数据集合")
    @PreAuthorize("@el.check('user:getPageByParam')")
    public ResultResponse getPageByParam(User record, Page page) {
        IPage<User> recordIPage = userService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperationSupport(order = 6)
    @Log("系统用户信息管理:根据条件查询符合数据的数量")
    @GetMapping("count")
    @ApiOperation("根据条件查询符合数据的数量")
    @PreAuthorize("@el.check('user:getCount')")
    public ResultResponse getCount(User record) {
        int result = userService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("系统用户信息管理:插入单条数据")
    @PreAuthorize("@el.check('user:insert')")
    public ResultResponse insert(@RequestBody User record) {
        int result = userService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 8)
    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    @Log("系统用户信息管理:批量插入数据")
    @PreAuthorize("@el.check('user:insertBatch')")
    public ResultResponse insertBatch(@RequestBody List<User> records) {
        int result = userService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 13)
    @ApiOperation("根据条件删除数据")
    @DeleteMapping("bySelective")
    @Log("系统用户信息管理:根据条件删除数据")
    @PreAuthorize("@el.check('user:deleteBySelective')")
    public ResultResponse deleteBySelective(@RequestBody User record) {
        int result = userService.deleteBySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 14)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("系统用户信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('user:delete')")
    public ResultResponse delete(Long id) {
        int result = userService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 15)
    @ApiOperation("根据主键集合批量删除数据")
    @DeleteMapping("ids")
    @Log("系统用户信息管理:根据主键集合批量删除数据")
    @PreAuthorize("@el.check('user:deleteByIds')")
    public ResultResponse deleteByIds(@RequestBody List<Long> ids) {
        int result = userService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
