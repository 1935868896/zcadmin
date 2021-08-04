package com.zc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.domain.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 系统日志 Mapper 接口
 * </p>
 *
 * @author ZhangC
 * @since 2021-08-03
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {
    @Select("select * from sys_log where log_type=#{logType}")
    IPage<SysLog> selectByLogType(Page<?> page, String logType);

}
