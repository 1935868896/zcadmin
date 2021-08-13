package com.zc.modules.quartz.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import com.zc.modules.quartz.entity.QuartzLog;
import com.zc.modules.quartz.service.QuartzLogService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 定时任务日志 信息操作处理
 *
 * @author Zhang C
 * @date 2021-08-12
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/quartz/quartzLog")
@Api(tags = "定时任务日志信息管理")
public class QuartzLogController {
    private final QuartzLogService quartzLogService;

    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("定时任务日志信息管理:根据主键获得对象数据")
    @PreAuthorize("@el.check('quartzLog:getRecordById')")
    public ResultResponse getRecordById(Long id) {
        QuartzLog result = quartzLogService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("定时任务日志信息管理:根据条件查询得到对象集合")
    @PreAuthorize("@el.check('quartzLog:getListByParam')")
    public ResultResponse getListByParam(QuartzLog record) {
        List<QuartzLog> result = quartzLogService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @GetMapping("/ids")
    @ApiOperation("根据id集合获得目标数据集合")
    @Log("定时任务日志信息管理:根据id集合获得目标数据集合")
    @PreAuthorize("@el.check('quartzLog:getListByIds')")
    public ResultResponse getListByIds(@RequestParam(value = "ids" ,required=false)List<Long> ids) {
        List<QuartzLog> result = quartzLogService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    @Log("定时任务日志信息管理:分页获得目标数据集合")
    @PreAuthorize("@el.check('quartzLog:getPageByParam')")
    public ResultResponse getPageByParam(QuartzLog record, Page page) {
        IPage<QuartzLog> recordIPage = quartzLogService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @Log("定时任务日志信息管理:根据条件查询符合数据的数量")
    @GetMapping("count")
    @ApiOperation("根据条件查询符合数据的数量")
    @PreAuthorize("@el.check('quartzLog:getCount')")
    public ResultResponse getCount(QuartzLog record) {
        int result = quartzLogService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("定时任务日志信息管理:插入单条数据")
    @PreAuthorize("@el.check('quartzLog:insert')")
    public ResultResponse insert(@RequestBody QuartzLog record) {
        int result = quartzLogService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    @Log("定时任务日志信息管理:批量插入数据")
    @PreAuthorize("@el.check('quartzLog:insertBatch')")
    public ResultResponse insertBatch(@RequestBody List<QuartzLog> records) {
        int result = quartzLogService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperation("修改数据")
    @PutMapping
    @Log("定时任务日志信息管理:修改数据")
    @PreAuthorize("@el.check('quartzLog:update')")
    public ResultResponse update(@RequestBody QuartzLog record) {
        int result = quartzLogService.updateByPrimaryKey(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    @Log("定时任务日志信息管理:修改部分数据")
    @PreAuthorize("@el.check('quartzLog:updateBySelective')")
    public ResultResponse updateBySelective(@RequestBody QuartzLog record) {
        int result = quartzLogService.updateByPrimaryKeySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    @Log("定时任务日志信息管理:批量修改数据")
    @PreAuthorize("@el.check('quartzLog:updateBatch')")
    public ResultResponse updateBatch(@RequestBody List<QuartzLog> records) {
        int result = quartzLogService.updateBatch(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    //如果某批数据中,有一个数据属性存在值,其他数据的属性不存在值,那么最终修改结果为其他数据的该属性将设为null值
    @ApiOperation("批量修改数据,仅修改部分属性")
    @PutMapping("batch/selective")
    @Log("定时任务日志信息管理:批量修改数据的部分属性")
    @PreAuthorize("@el.check('quartzLog:updateBatchBySelective')")
    public ResultResponse updateBatchBySelective(@RequestBody List<QuartzLog> records) {
        int result = quartzLogService.updateBatchSelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("删除数据")
    @DeleteMapping()
    @Log("定时任务日志信息管理:删除数据")
    @PreAuthorize("@el.check('quartzLog:delete')")
    public ResultResponse delete(Long id) {
        int result = quartzLogService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("根据主键集合批量删除数据")
    @DeleteMapping("ids")
    @Log("定时任务日志信息管理:根据主键集合批量删除数据")
    @PreAuthorize("@el.check('quartzLog:deleteByIds')")
    public ResultResponse deleteByIds(@RequestBody List<Long> ids) {
        int result = quartzLogService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
