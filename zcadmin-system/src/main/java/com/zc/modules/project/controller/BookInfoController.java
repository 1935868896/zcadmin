package com.zc.modules.project.controller;
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
    import com.zc.modules.project.entity.BookInfo;
import com.zc.modules.project.service.BookInfoService;

/**
 * 图书 信息操作处理
 *
 * @author zhangc
 * @date 2021-09-09
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/bookInfo")
@Api(tags = "图书信息管理")
public class BookInfoController {
    private final BookInfoService bookInfoService;

    @ApiOperationSupport(order = 1)
    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("图书信息管理:根据主键获得对象数据")
    @PreAuthorize("@el.check('bookInfo:getRecordById')")
    public ResultResponse getRecordById(Integer id) {
        BookInfo result = bookInfoService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 2)
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("图书信息管理:根据条件查询得到对象集合")
    @PreAuthorize("@el.check('bookInfo:getListByParam')")
    public ResultResponse getListByParam(BookInfo record) {
        List<BookInfo> result = bookInfoService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 3)
    @GetMapping("/single")
    @ApiOperation("根据条件查询得到单个对象")
    @Log("图书信息管理:根据条件查询得到单个对象")
    @PreAuthorize("@el.check('bookInfo:getOneByParam')")
    public ResultResponse getOneByParam(BookInfo record) {
        BookInfo result = bookInfoService.selectOneBySelective(record);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 4)
    @GetMapping("/ids")
    @ApiOperation("根据id集合获得目标数据集合")
    @Log("图书信息管理:根据id集合获得目标数据集合")
    @PreAuthorize("@el.check('bookInfo:getListByIds')")
    public ResultResponse getListByIds(@RequestParam(value = "ids", required = false) List<Integer> ids) {
        List<BookInfo> result = bookInfoService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    @Log("图书信息管理:分页获得目标数据集合")
    @PreAuthorize("@el.check('bookInfo:getPageByParam')")
    public ResultResponse getPageByParam(BookInfo record, Page page) {
        IPage<BookInfo> recordIPage = bookInfoService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperationSupport(order = 6)
    @Log("图书信息管理:根据条件查询符合数据的数量")
    @GetMapping("count")
    @ApiOperation("根据条件查询符合数据的数量")
    @PreAuthorize("@el.check('bookInfo:getCount')")
    public ResultResponse getCount(BookInfo record) {
        int result = bookInfoService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("图书信息管理:插入单条数据")
    @PreAuthorize("@el.check('bookInfo:insert')")
    public ResultResponse insert(@RequestBody BookInfo record) {
        int result = bookInfoService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 8)
    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    @Log("图书信息管理:批量插入数据")
    @PreAuthorize("@el.check('bookInfo:insertBatch')")
    public ResultResponse insertBatch(@RequestBody List<BookInfo> records) {
        int result = bookInfoService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("图书信息管理:修改数据")
    @PreAuthorize("@el.check('bookInfo:update')")
    public ResultResponse update(@RequestBody BookInfo record) {
        int result = bookInfoService.update(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 10)
    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    @Log("图书信息管理:修改部分数据")
    @PreAuthorize("@el.check('bookInfo:updateBySelective')")
    public ResultResponse updateSelective(@RequestBody BookInfo record) {
        int result = bookInfoService.updateSelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 11)
    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    @Log("图书信息管理:批量修改数据")
    @PreAuthorize("@el.check('bookInfo:updateBatch')")
    public ResultResponse updateBatch(@RequestBody List<BookInfo> records) {
        int result = bookInfoService.updateBatch(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    //如果某批数据中,有一个数据属性存在值,其他数据的属性不存在值,那么最终修改结果为其他数据的该属性将设为null值
    @ApiOperationSupport(order = 12)
    @ApiOperation("批量修改数据,仅修改部分属性")
    @PutMapping("batch/selective")
    @Log("图书信息管理:批量修改数据的部分属性")
    @PreAuthorize("@el.check('bookInfo:updateBatchBySelective')")
    public ResultResponse updateBatchBySelective(@RequestBody List<BookInfo> records) {
        int result = bookInfoService.updateBatchBySelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 13)
    @ApiOperation("根据条件删除数据")
    @DeleteMapping("bySelective")
    @Log("图书信息管理:根据条件删除数据")
    @PreAuthorize("@el.check('bookInfo:deleteBySelective')")
    public ResultResponse deleteBySelective(@RequestBody BookInfo record) {
        int result = bookInfoService.deleteBySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 14)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("图书信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('bookInfo:delete')")
    public ResultResponse delete(Integer id) {
        int result = bookInfoService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 15)
    @ApiOperation("根据主键集合批量删除数据")
    @DeleteMapping("ids")
    @Log("图书信息管理:根据主键集合批量删除数据")
    @PreAuthorize("@el.check('bookInfo:deleteByIds')")
    public ResultResponse deleteByIds(@RequestBody List<Integer> ids) {
        int result = bookInfoService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
