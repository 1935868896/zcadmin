
package com.zc.modules.test.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import com.zc.modules.test.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.zc.modules.test.entity.Book;

/**
 * @author ZhangC
 * @create 2021-08-04-14:34
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test/book")
@Api(tags = "图书管理")
public class BookController {
    private final BookService bookService;

    @ApiOperation("查询数量")
    @GetMapping("count")
    @Log
    public ResultResponse count(Book record) {
        int result = bookService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @ApiOperation("得到目标数据集合")
    @GetMapping
    @Log
    public ResultResponse get(Book record) {
        List<Book> result = bookService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperation("根据id获得对象数据")
    @GetMapping("/id")
    public ResultResponse getById(Book record) {
        Book result = bookService.selectByPrimaryKey(record.getId());
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperation("根据id集合获得目标数据集合")
    @GetMapping("/ids")
    public ResultResponse getByIds(List<Integer> ids) {
        List<Book> result = bookService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    public ResultResponse getPageResults(Book record, Page page) {
        IPage<Book> bookIPage = bookService.selectPageBySelective(record, page);
        return ResultResponse.success(bookIPage);
    }

    @ApiOperation("插入单条数据")
    @PostMapping
    public ResultResponse insert(@RequestBody Book record) {
        int result = bookService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    public ResultResponse insertBatch(@RequestBody List<Book> records) {
        int result = bookService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperation("修改数据")
    @PutMapping
    public ResultResponse update(@RequestBody Book record) {
        int result = bookService.updateByPrimaryKey(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    public ResultResponse updateByPrimaryKeySelective(Book record) {
        int result = bookService.updateByPrimaryKeySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    public ResultResponse updateBatch(List<Book> records) {
        int result = bookService.updateBatch(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("批量修改数据,仅修改不为null的数据")
    @PutMapping("batch/selective")
    public ResultResponse updateBatchSelective(List<Book> records) {
        int result = bookService.updateBatchSelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("删除数据")
    @DeleteMapping()
    public ResultResponse delete(Integer id) {
        int result = bookService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("根据id集合批量删除数据")
    @DeleteMapping("ids")
    public ResultResponse deleteBatch(List<Integer> ids) {
        int result = bookService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
