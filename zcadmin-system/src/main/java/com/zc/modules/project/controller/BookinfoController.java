package com.zc.modules.project.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import com.zc.modules.project.entity.Bookinfo;
import com.zc.modules.project.service.BookinfoService;
/**
 * 图书 信息操作处理
 *
 * @author ruoyi
 * @date 2021-08-09
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/bookinfo")
@Api(tags = "图书信息管理")
public class BookinfoController {
    private final BookinfoService bookinfoService;



    @Log
    @GetMapping("count")
    @ApiOperation("根据条件查询数量")
    public ResultResponse count(Bookinfo record) {
        int result = bookinfoService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @Log
    @GetMapping
    @ApiOperation("根据条件得到数据 List<Object> 集合")
    public ResultResponse get(Bookinfo record) {
        List<Bookinfo> result = bookinfoService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

        @Log
    @GetMapping("/id")
    @ApiOperation("根据id获得对象数据")
    public ResultResponse getById(Integer id) {
        Bookinfo result = bookinfoService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @GetMapping("/ids")
    @ApiOperation("根据id集合获得目标数据集合")
    public ResultResponse getByIds(@RequestParam(value = "ids" ,required=false)List<Integer> ids) {
        List<Bookinfo> result = bookinfoService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    public ResultResponse getPageResults(Bookinfo record, Page page) {
        IPage<Bookinfo> recordIPage = bookinfoService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperation("插入单条数据")
    @PostMapping
    public ResultResponse insert(@RequestBody Bookinfo record) {
        int result = bookinfoService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    public ResultResponse insertBatch(@RequestBody List<Bookinfo> records) {
        int result = bookinfoService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperation("修改数据")
    @PutMapping
    public ResultResponse update(@RequestBody Bookinfo record) {
        int result = bookinfoService.updateByPrimaryKey(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    public ResultResponse updateByPrimaryKeySelective(@RequestBody Bookinfo record) {
        int result = bookinfoService.updateByPrimaryKeySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    public ResultResponse updateBatch(@RequestBody List<Bookinfo> records) {
        int result = bookinfoService.updateBatch(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("批量修改数据,仅修改不为null的数据")
    @PutMapping("batch/selective")
    public ResultResponse updateBatchSelective(@RequestBody List<Bookinfo> records) {
        int result = bookinfoService.updateBatchSelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("删除数据")
    @DeleteMapping()
    public ResultResponse delete(Integer id) {
        int result = bookinfoService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperation("根据id集合批量删除数据")
    @DeleteMapping("ids")
    public ResultResponse deleteBatch(@RequestBody List<Integer> ids) {
        int result = bookinfoService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
