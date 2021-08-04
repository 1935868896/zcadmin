package com.zc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.domain.SysLog;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author ZhangC
 * @since 2021-08-03
 */
public interface ISysLogService extends IService<SysLog> {

    void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, SysLog log);

    IPage<SysLog> selectByLogType(Page<?> page, String logType);
}
