package com.zc.modules.quartz.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.annoation.Anonymous;
import com.zc.modules.quartz.QuartzScheduleHandle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import com.zc.modules.quartz.entity.QuartzJob;
import com.zc.modules.quartz.service.QuartzJobService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 定时任务 信息操作处理
 *
 * @author Zhang C
 * @date 2021-08-12
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/quartz/quartzJob")
@Api(tags = "定时任务信息管理")
public class QuartzJobController {
    private final QuartzJobService quartzJobService;
    private final QuartzScheduleHandle quartzScheduleHandle;

    /**
     * 1.任务创建
     * 2.暂停
     * 3.恢复
     * 4.删除
     * 5.修改
     * 6.立即执行
     */

    @ApiOperation("创建任务")
    @PostMapping
    @Log("定时任务: 创建任务")
//    @PreAuthorize("@el.check('quartzJob:create')")
   @Anonymous
    public ResultResponse create(@RequestBody QuartzJob record) {
        int result = quartzJobService.create(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperation("暂停任务")
    @PutMapping("pause")
    @Log("定时任务: 暂停任务")
    @Anonymous
    public ResultResponse pause(@RequestBody QuartzJob record) {
        quartzJobService.pause(record);
        return ResultResponse.success();
    }

    @ApiOperation("恢复任务")
    @PutMapping("resume")
    @Log("定时任务: 恢复任务")
    @Anonymous
    public ResultResponse resume(@RequestBody QuartzJob record) {
        quartzJobService.resume(record);
        return ResultResponse.success();
    }

    @ApiOperation("删除任务")
    @DeleteMapping("delete")
    @Log("定时任务: 删除任务")
    @Anonymous
    public ResultResponse deleteTask(@RequestBody QuartzJob record) {
        quartzJobService.delete(record);
        return ResultResponse.success();
    }

    @ApiOperation("立即执行任务")
    @DeleteMapping("runNow")
    @Log("定时任务: 立即执行任务")
    @Anonymous
    public ResultResponse runNow(@RequestBody QuartzJob record) {
        quartzJobService.runNow(record);
        return ResultResponse.success();
    }





//    @GetMapping("/id")
//    @ApiOperation("根据主键获得对象数据")
//    @Log("定时任务信息管理:根据主键获得对象数据")
//    @PreAuthorize("@el.check('quartzJob:getRecordById')")
//    public ResultResponse getRecordById(Long id) {
//        QuartzJob result = quartzJobService.selectByPrimaryKey(id);
//        if (result != null) {
//            return ResultResponse.success(result);
//        }
//        return ResultResponse.error();
//    }
//
//    @GetMapping
//    @ApiOperation("根据条件查询得到对象集合")
//    @Log("定时任务信息管理:根据条件查询得到对象集合")
//    @PreAuthorize("@el.check('quartzJob:getListByParam')")
//    public ResultResponse getListByParam(QuartzJob record) {
//        List<QuartzJob> result = quartzJobService.selectListBySelective(record);
//        if (result != null && result.size() > 0) {
//            return ResultResponse.success(result);
//        }
//        return ResultResponse.error();
//    }
//
//
//    @GetMapping("/ids")
//    @ApiOperation("根据id集合获得目标数据集合")
//    @Log("定时任务信息管理:根据id集合获得目标数据集合")
//    @PreAuthorize("@el.check('quartzJob:getListByIds')")
//    public ResultResponse getListByIds(@RequestParam(value = "ids" ,required=false)List<Long> ids) {
//        List<QuartzJob> result = quartzJobService.selectByPrimaryKeys(ids);
//        if (result != null && result.size() > 0) {
//            return ResultResponse.success(result);
//        }
//        return ResultResponse.error();
//    }
//
//    @ApiOperation("分页获得目标数据集合")
//    @GetMapping("page")
//    @Log("定时任务信息管理:分页获得目标数据集合")
//    @PreAuthorize("@el.check('quartzJob:getPageByParam')")
//    public ResultResponse getPageByParam(QuartzJob record, Page page) {
//        IPage<QuartzJob> recordIPage = quartzJobService.selectPageBySelective(record, page);
//        return ResultResponse.success(recordIPage);
//    }
//
//    @Log("定时任务信息管理:根据条件查询符合数据的数量")
//    @GetMapping("count")
//    @ApiOperation("根据条件查询符合数据的数量")
//    @PreAuthorize("@el.check('quartzJob:getCount')")
//    public ResultResponse getCount(QuartzJob record) {
//        int result = quartzJobService.selectCountBySelective(record);
//        return ResultResponse.success(result);
//    }
//
//
//
//    @ApiOperation("批量插入数据")
//    @PostMapping("batch")
//    @Log("定时任务信息管理:批量插入数据")
//    @PreAuthorize("@el.check('quartzJob:insertBatch')")
//    public ResultResponse insertBatch(@RequestBody List<QuartzJob> records) {
//        int result = quartzJobService.insertBatch(records);
//        if (result > 0) {
//            return ResultResponse.success(records);
//        }
//        return ResultResponse.error();
//    }
//
//    @ApiOperation("修改数据")
//    @PutMapping
//    @Log("定时任务信息管理:修改数据")
//    @PreAuthorize("@el.check('quartzJob:update')")
//    public ResultResponse update(@RequestBody QuartzJob record) {
//        int result = quartzJobService.updateByPrimaryKey(record);
//        if (result > 0) {
//            return ResultResponse.success();
//        }
//        return ResultResponse.error();
//    }
//
//    @ApiOperation("修改数据,仅修改不为null的数据")
//    @PutMapping("/selective")
//    @Log("定时任务信息管理:修改部分数据")
//    @PreAuthorize("@el.check('quartzJob:updateBySelective')")
//    public ResultResponse updateBySelective(@RequestBody QuartzJob record) {
//        int result = quartzJobService.updateByPrimaryKeySelective(record);
//        if (result > 0) {
//            return ResultResponse.success();
//        }
//        return ResultResponse.error();
//    }
//
//    @ApiOperation("批量修改数据")
//    @PutMapping("batch")
//    @Log("定时任务信息管理:批量修改数据")
//    @PreAuthorize("@el.check('quartzJob:updateBatch')")
//    public ResultResponse updateBatch(@RequestBody List<QuartzJob> records) {
//        int result = quartzJobService.updateBatch(records);
//        if (result > 0) {
//            return ResultResponse.success();
//        }
//        return ResultResponse.error();
//    }
//
//    //如果某批数据中,有一个数据属性存在值,其他数据的属性不存在值,那么最终修改结果为其他数据的该属性将设为null值
//    @ApiOperation("批量修改数据,仅修改部分属性")
//    @PutMapping("batch/selective")
//    @Log("定时任务信息管理:批量修改数据的部分属性")
//    @PreAuthorize("@el.check('quartzJob:updateBatchBySelective')")
//    public ResultResponse updateBatchBySelective(@RequestBody List<QuartzJob> records) {
//        int result = quartzJobService.updateBatchSelective(records);
//        if (result > 0) {
//            return ResultResponse.success();
//        }
//        return ResultResponse.error();
//    }
//
//    @ApiOperation("删除数据")
//    @DeleteMapping()
//    @Log("定时任务信息管理:删除数据")
//    @PreAuthorize("@el.check('quartzJob:delete')")
//    public ResultResponse delete(Long id) {
//        int result = quartzJobService.deleteByPrimaryKey(id);
//        if (result > 0) {
//            return ResultResponse.success();
//        }
//        return ResultResponse.error();
//    }
//
//    @ApiOperation("根据主键集合批量删除数据")
//    @DeleteMapping("ids")
//    @Log("定时任务信息管理:根据主键集合批量删除数据")
//    @PreAuthorize("@el.check('quartzJob:deleteByIds')")
//    public ResultResponse deleteByIds(@RequestBody List<Long> ids) {
//        int result = quartzJobService.deleteByPrimaryKeys(ids);
//        if (result > 0) {
//            return ResultResponse.success();
//        }
//        return ResultResponse.error();
//    }
//

}
