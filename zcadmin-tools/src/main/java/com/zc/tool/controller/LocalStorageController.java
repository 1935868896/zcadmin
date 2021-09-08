package com.zc.tool.controller;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    import com.zc.tool.entity.LocalStorage;
    import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
    import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
    import com.zc.tool.service.LocalStorageService;

/**
 * 本地存储 信息操作处理
 *
 * @author zhangc
 * @date 2021-09-03
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/tool/localStorage")
@Api(tags = "本地存储信息管理")
public class LocalStorageController {
    private final LocalStorageService localStorageService;

    @ApiOperationSupport(order = 1)
    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("本地存储信息管理:根据主键获得对象数据")
    @PreAuthorize("@el.check('localStorage:getRecordById')")
    public ResultResponse getRecordById(Long id) {
        LocalStorage result = localStorageService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 2)
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("本地存储信息管理:根据条件查询得到对象集合")
    @PreAuthorize("@el.check('localStorage:getListByParam')")
    public ResultResponse getListByParam(LocalStorage record) {
        List<LocalStorage> result = localStorageService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 3)
    @GetMapping("/single")
    @ApiOperation("根据条件查询得到单个对象")
    @Log("本地存储信息管理:根据条件查询得到单个对象")
    @PreAuthorize("@el.check('localStorage:getOneByParam')")
    public ResultResponse getOneByParam(LocalStorage record) {
        LocalStorage result = localStorageService.selectOneBySelective(record);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 4)
    @GetMapping("/ids")
    @ApiOperation("根据id集合获得目标数据集合")
    @Log("本地存储信息管理:根据id集合获得目标数据集合")
    @PreAuthorize("@el.check('localStorage:getListByIds')")
    public ResultResponse getListByIds(@RequestParam(value = "ids", required = false) List<Long> ids) {
        List<LocalStorage> result = localStorageService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    @Log("本地存储信息管理:分页获得目标数据集合")
    @PreAuthorize("@el.check('localStorage:getPageByParam')")
    public ResultResponse getPageByParam(LocalStorage record, Page page) {
        IPage<LocalStorage> recordIPage = localStorageService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperationSupport(order = 6)
    @Log("本地存储信息管理:根据条件查询符合数据的数量")
    @GetMapping("count")
    @ApiOperation("根据条件查询符合数据的数量")
    @PreAuthorize("@el.check('localStorage:getCount')")
    public ResultResponse getCount(LocalStorage record) {
        int result = localStorageService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("本地存储信息管理:插入单条数据")
    @PreAuthorize("@el.check('localStorage:insert')")
    public ResultResponse insert(@RequestBody LocalStorage record) {
        int result = localStorageService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 8)
    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    @Log("本地存储信息管理:批量插入数据")
    @PreAuthorize("@el.check('localStorage:insertBatch')")
    public ResultResponse insertBatch(@RequestBody List<LocalStorage> records) {
        int result = localStorageService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("本地存储信息管理:修改数据")
    @PreAuthorize("@el.check('localStorage:update')")
    public ResultResponse update(@RequestBody LocalStorage record) {
        int i = localStorageService.selectCountName(record);
        if (i>0){
         return ResultResponse.error("文件名字重复");
        }
        int result = localStorageService.update(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 10)
    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    @Log("本地存储信息管理:修改部分数据")
    @PreAuthorize("@el.check('localStorage:updateBySelective')")
    public ResultResponse updateSelective(@RequestBody LocalStorage record) {
        int result = localStorageService.updateSelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 11)
    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    @Log("本地存储信息管理:批量修改数据")
    @PreAuthorize("@el.check('localStorage:updateBatch')")
    public ResultResponse updateBatch(@RequestBody List<LocalStorage> records) {
        int result = localStorageService.updateBatch(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    //如果某批数据中,有一个数据属性存在值,其他数据的属性不存在值,那么最终修改结果为其他数据的该属性将设为null值
    @ApiOperationSupport(order = 12)
    @ApiOperation("批量修改数据,仅修改部分属性")
    @PutMapping("batch/selective")
    @Log("本地存储信息管理:批量修改数据的部分属性")
    @PreAuthorize("@el.check('localStorage:updateBatchBySelective')")
    public ResultResponse updateBatchBySelective(@RequestBody List<LocalStorage> records) {
        int result = localStorageService.updateBatchBySelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 13)
    @ApiOperation("根据条件删除数据")
    @DeleteMapping("bySelective")
    @Log("本地存储信息管理:根据条件删除数据")
    @PreAuthorize("@el.check('localStorage:deleteBySelective')")
    public ResultResponse deleteBySelective(@RequestBody LocalStorage record) {
        int result = localStorageService.deleteBySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 14)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("本地存储信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('localStorage:delete')")
    public ResultResponse delete(Long id) {
        int result = localStorageService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 15)
    @ApiOperation("根据主键集合批量删除数据")
    @DeleteMapping("ids")
    @Log("本地存储信息管理:根据主键集合批量删除数据")
    @PreAuthorize("@el.check('localStorage:deleteByIds')")
    public ResultResponse deleteByIds(@RequestBody List<Long> ids) {
        int result = localStorageService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
