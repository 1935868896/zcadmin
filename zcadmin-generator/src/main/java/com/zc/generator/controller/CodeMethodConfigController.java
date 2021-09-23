package com.zc.generator.controller;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    import com.zc.exception.BadRequestException;
    import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import com.zc.generator.entity.CodeMethodConfig;
import com.zc.generator.service.CodeMethodConfigService;


/**
 * 代码生成方法 信息操作处理
 *
 * @author zhangc
 * @date 2021-09-23
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/generator/codeMethodConfig")
@Api(tags = "代码生成方法信息管理")
public class CodeMethodConfigController {
    private final CodeMethodConfigService codeMethodConfigService;

    @ApiOperationSupport(order = 1)
    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("代码生成方法信息管理:根据主键获得对象数据")
    @PreAuthorize("@el.check('codeMethodConfig:getRecordById')")
    public ResultResponse getRecordById(Integer id) {
        CodeMethodConfig result = codeMethodConfigService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 2)
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("代码生成方法信息管理:根据条件查询得到对象集合")
    @PreAuthorize("@el.check('codeMethodConfig:getListByParam')")
    public ResultResponse getListByParam(CodeMethodConfig record) {
        List<CodeMethodConfig> result = codeMethodConfigService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 3)
    @GetMapping("/single")
    @ApiOperation("根据条件查询得到单个对象")
    @Log("代码生成方法信息管理:根据条件查询得到单个对象")
    @PreAuthorize("@el.check('codeMethodConfig:getOneByParam')")
    public ResultResponse getOneByParam(CodeMethodConfig record) {
        CodeMethodConfig result = codeMethodConfigService.selectOneBySelective(record);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 4)
    @GetMapping("/ids")
    @ApiOperation("根据id集合获得目标数据集合")
    @Log("代码生成方法信息管理:根据id集合获得目标数据集合")
    @PreAuthorize("@el.check('codeMethodConfig:getListByIds')")
    public ResultResponse getListByIds(@RequestParam(value = "ids", required = false) List<Integer> ids) {
        List<CodeMethodConfig> result = codeMethodConfigService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    @Log("代码生成方法信息管理:分页获得目标数据集合")
    @PreAuthorize("@el.check('codeMethodConfig:getPageByParam')")
    public ResultResponse getPageByParam(CodeMethodConfig record, Page page) {
        IPage<CodeMethodConfig> recordIPage = codeMethodConfigService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperationSupport(order = 6)
    @Log("代码生成方法信息管理:根据条件查询符合数据的数量")
    @GetMapping("count")
    @ApiOperation("根据条件查询符合数据的数量")
    @PreAuthorize("@el.check('codeMethodConfig:getCount')")
    public ResultResponse getCount(CodeMethodConfig record) {
        int result = codeMethodConfigService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("代码生成方法信息管理:插入单条数据")
    @PreAuthorize("@el.check('codeMethodConfig:insert')")
    public ResultResponse insert(@RequestBody CodeMethodConfig record) {
        if (record==null){
            throw new BadRequestException("插入数据为空");
        }
        int result = codeMethodConfigService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 8)
    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    @Log("代码生成方法信息管理:批量插入数据")
    @PreAuthorize("@el.check('codeMethodConfig:insertBatch')")
    public ResultResponse insertBatch(@RequestBody List<CodeMethodConfig> records) {
        if (records.isEmpty()){
            throw new BadRequestException("批量插入数据为空");
        }
        int result = codeMethodConfigService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("代码生成方法信息管理:修改数据")
    @PreAuthorize("@el.check('codeMethodConfig:update')")
    public ResultResponse update(@RequestBody CodeMethodConfig record) {
        if (record==null){
            throw new BadRequestException("修改数据为空");
        }
        int result = codeMethodConfigService.update(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 10)
    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    @Log("代码生成方法信息管理:修改部分数据")
    @PreAuthorize("@el.check('codeMethodConfig:updateBySelective')")
    public ResultResponse updateSelective(@RequestBody CodeMethodConfig record) {
        if (record==null){
            throw new BadRequestException("修改数据为空");
        }
        int result = codeMethodConfigService.updateSelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 11)
    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    @Log("代码生成方法信息管理:批量修改数据")
    @PreAuthorize("@el.check('codeMethodConfig:updateBatch')")
    public ResultResponse updateBatch(@RequestBody List<CodeMethodConfig> records) {
        if (records.isEmpty()){
            throw new BadRequestException("批量修改数据为空");
        }
        int result = codeMethodConfigService.updateBatch(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    //如果某批数据中,有一个数据属性存在值,其他数据的属性不存在值,那么最终修改结果为其他数据的该属性将设为null值
    @ApiOperationSupport(order = 12)
    @ApiOperation("批量修改数据,仅修改部分属性")
    @PutMapping("batch/selective")
    @Log("代码生成方法信息管理:批量修改数据的部分属性")
    @PreAuthorize("@el.check('codeMethodConfig:updateBatchBySelective')")
    public ResultResponse updateBatchBySelective(@RequestBody List<CodeMethodConfig> records) {
        if (records.isEmpty()){
            throw new BadRequestException("批量修改数据为空");
        }






        int result = codeMethodConfigService.updateBatchBySelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 13)
    @ApiOperation("根据条件删除数据")
    @DeleteMapping("bySelective")
    @Log("代码生成方法信息管理:根据条件删除数据")
    @PreAuthorize("@el.check('codeMethodConfig:deleteBySelective')")
    public ResultResponse deleteBySelective(@RequestBody CodeMethodConfig record) {
        int result = codeMethodConfigService.deleteBySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 14)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("代码生成方法信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('codeMethodConfig:delete')")
    public ResultResponse delete(Integer id) {
        int result = codeMethodConfigService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 15)
    @ApiOperation("根据主键集合批量删除数据")
    @DeleteMapping("ids")
    @Log("代码生成方法信息管理:根据主键集合批量删除数据")
    @PreAuthorize("@el.check('codeMethodConfig:deleteByIds')")
    public ResultResponse deleteByIds(@RequestBody List<Integer> ids) {
        int result = codeMethodConfigService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
