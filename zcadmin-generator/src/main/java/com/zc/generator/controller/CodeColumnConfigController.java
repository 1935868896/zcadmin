package com.zc.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.annoation.Anonymous;
import com.zc.generator.entity.CodeColumnConfig;
import com.zc.generator.service.CodeColumnConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 代码生成字段存储 信息操作处理
 *
 * @author zhangc
 * @date 2021-08-24
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/gen/codeColumnConfig")
@Api(tags = "代码生成字段存储信息管理")
@ApiIgnore
public class CodeColumnConfigController {
    private final CodeColumnConfigService codeColumnConfigService;

    @ApiOperationSupport(order = 1)
    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("代码生成字段存储信息管理:根据主键获得对象数据")
    @PreAuthorize("@el.check('codeColumnConfig:getRecordById')")
    public ResultResponse getRecordById(Long id) {
        CodeColumnConfig result = codeColumnConfigService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 2)
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("代码生成字段存储信息管理:根据条件查询得到对象集合")
    @PreAuthorize("@el.check('codeColumnConfig:getListByParam')")
    public ResultResponse getListByParam(CodeColumnConfig record) {
        List<CodeColumnConfig> result = codeColumnConfigService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 3)
    @GetMapping("/ids")
    @ApiOperation("根据id集合获得目标数据集合")
    @Log("代码生成字段存储信息管理:根据id集合获得目标数据集合")
    @PreAuthorize("@el.check('codeColumnConfig:getListByIds')")
    public ResultResponse getListByIds(@RequestParam(value = "ids", required = false) List<Long> ids) {
        List<CodeColumnConfig> result = codeColumnConfigService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    @Log("代码生成字段存储信息管理:分页获得目标数据集合")
//    @PreAuthorize("@el.check('codeColumnConfig:getPageByParam')")
    @Anonymous
    public ResultResponse getPageByParam(CodeColumnConfig record, Page page) {
        IPage<CodeColumnConfig> recordIPage = codeColumnConfigService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperationSupport(order = 5)
    @Log("代码生成字段存储信息管理:根据条件查询符合数据的数量")
    @GetMapping("count")
    @ApiOperation("根据条件查询符合数据的数量")
    @PreAuthorize("@el.check('codeColumnConfig:getCount')")
    public ResultResponse getCount(CodeColumnConfig record) {
        int result = codeColumnConfigService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @ApiOperationSupport(order = 6)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("代码生成字段存储信息管理:插入单条数据")
    @PreAuthorize("@el.check('codeColumnConfig:insert')")
    public ResultResponse insert(@RequestBody CodeColumnConfig record) {
        int result = codeColumnConfigService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    @Log("代码生成字段存储信息管理:批量插入数据")
    @PreAuthorize("@el.check('codeColumnConfig:insertBatch')")
    public ResultResponse insertBatch(@RequestBody List<CodeColumnConfig> records) {
        int result = codeColumnConfigService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 8)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("代码生成字段存储信息管理:修改数据")
    @PreAuthorize("@el.check('codeColumnConfig:update')")
    public ResultResponse update(@RequestBody CodeColumnConfig record) {
        int result = codeColumnConfigService.update(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    @Log("代码生成字段存储信息管理:修改部分数据")
    @PreAuthorize("@el.check('codeColumnConfig:updateBySelective')")
    public ResultResponse updateBySelective(@RequestBody CodeColumnConfig record) {
        int result = codeColumnConfigService.updateBySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 10)
    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    @Log("代码生成字段存储信息管理:批量修改数据")
//    @PreAuthorize("@el.check('codeColumnConfig:updateBatch')")
    @Anonymous
    public ResultResponse updateBatch(@RequestBody List<CodeColumnConfig> records) {
        int result = codeColumnConfigService.updateBatch(records);

        return ResultResponse.success();
    }

    //如果某批数据中,有一个数据属性存在值,其他数据的属性不存在值,那么最终修改结果为其他数据的该属性将设为null值
    @ApiOperationSupport(order = 11)
    @ApiOperation("批量修改数据,仅修改部分属性")
    @PutMapping("batch/selective")
    @Log("代码生成字段存储信息管理:批量修改数据的部分属性")
    @PreAuthorize("@el.check('codeColumnConfig:updateBatchBySelective')")
    public ResultResponse updateBatchBySelective(@RequestBody List<CodeColumnConfig> records) {
        int result = codeColumnConfigService.updateBatchBySelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 12)
    @ApiOperation("删除数据")
    @DeleteMapping()
    @Log("代码生成字段存储信息管理:删除数据")
    @PreAuthorize("@el.check('codeColumnConfig:delete')")
    public ResultResponse delete(Long id) {
        int result = codeColumnConfigService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 13)
    @ApiOperation("根据主键集合批量删除数据")
    @DeleteMapping("ids")
    @Log("代码生成字段存储信息管理:根据主键集合批量删除数据")
    @PreAuthorize("@el.check('codeColumnConfig:deleteByIds')")
    public ResultResponse deleteByIds(@RequestBody List<Long> ids) {
        int result = codeColumnConfigService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
