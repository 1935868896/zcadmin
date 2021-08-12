package com.zc.modules.project.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.annoation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import com.zc.modules.project.entity.BookInfo;
import com.zc.modules.project.service.BookInfoService;
/**
 * 图书 信息操作处理
 *  1. 加权限 2. 改方法名
 *
 * @author ruoyi
 * @date 2021-08-10
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/bookInfo")
@Api(tags = "图书信息管理")
public class BookInfoController {
    private final BookInfoService bookInfoService;



    @Log("")
    @GetMapping("count")
    @ApiOperation("根据条件查询符合数据的数量")
    @PreAuthorize("@el.check('bookInfo:getCount')")
    public ResultResponse getCount(BookInfo record) {
        int result = bookInfoService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @Log
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @PreAuthorize("@el.check('bookInfo:get')")
    public ResultResponse getListByParam(BookInfo record) {
        List<BookInfo> result = bookInfoService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @Log
    @GetMapping("/id")
    @ApiOperation("根据id获得对象数据")
    public ResultResponse getRecordById(Integer id) {
        BookInfo result = bookInfoService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @GetMapping("/ids")
    @ApiOperation("根据id集合获得目标数据集合")
    public ResultResponse getListByIds(@RequestParam(value = "ids" ,required=false)List<Integer> ids) {
        List<BookInfo> result = bookInfoService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    public ResultResponse getPageByParam(BookInfo record, Page page) {
        IPage<BookInfo> recordIPage = bookInfoService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperation("插入单条数据")
    @PostMapping
    public ResultResponse insert(@RequestBody BookInfo record) {
        int result = bookInfoService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    public ResultResponse insertBatch(@RequestBody List<BookInfo> records) {
        int result = bookInfoService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperation("修改数据")
    @PutMapping
    public ResultResponse update(@RequestBody BookInfo record) {
        int result = bookInfoService.updateByPrimaryKey(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }
    @Anonymous
    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    public ResultResponse updateBySelective(@RequestBody BookInfo record) {
        int result = bookInfoService.updateByPrimaryKeySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    @PreAuthorize("@el.check('bookInfo:updateBatch')")
    public ResultResponse updateBatch(@RequestBody List<BookInfo> records) {
        int result = bookInfoService.updateBatch(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("批量修改数据,仅修改不为null的数据,传来的对象必须全部赋值想要修改的某属性,未赋值的数值会赋值为空")
    @PutMapping("batch/selective")
    public ResultResponse updateBatchBySelective(@RequestBody List<BookInfo> records) {
        int result = bookInfoService.updateBatchSelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("删除数据")
    @DeleteMapping()
    public ResultResponse delete(Integer id) {
        int result = bookInfoService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("根据id集合批量删除数据")
    @DeleteMapping("ids")
    public ResultResponse deleteBatch(@RequestBody List<Integer> ids) {
        int result = bookInfoService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
