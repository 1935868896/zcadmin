/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.zc.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.domain.SysLog;
import com.zc.entity.ResultResponse;
import com.zc.service.ISysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zheng Jie
 * @date 2018-11-24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs")
@Api(tags = "系统：日志管理")
public class LogController {

    private final ISysLogService logService;


    @PostMapping
    @ApiOperation("日志查询")
    public ResultResponse query(SysLog log, @RequestBody Page page){
        IPage<SysLog> sysLogIPage = logService.selectByLogType(page, log.getLogType());

        return ResultResponse.success(sysLogIPage);
    }

    @GetMapping(value = "/error/{id}")
    @ApiOperation("日志异常详情查询")
    public  ResultResponse queryErrorLogs(@PathVariable Long id){
        return ResultResponse.success(logService.findByErrDetail(id));
    }

}
