package com.zc.modules.quartz.service;

import com.zc.modules.quartz.entity.QuartzLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 定时任务日志 服务层
 *
 * @author Zhang C
 * @date 2021-08-12
 */
public interface QuartzLogService extends IService<QuartzLog> {
    /**
     * 查询定时任务日志信息
     *
     * @param id 定时任务日志ID
     * @return 定时任务日志信息
     */
    QuartzLog selectByPrimaryKey(Long id);


    /**
     * 根据条件,查询定时任务日志列表
     *
     * @param record 定时任务日志信息
     * @return 定时任务日志集合
     */
    List<QuartzLog> selectListBySelective(QuartzLog record);

    /**
     * 根据条件,分页查询定时任务日志列表
     *
     * @param record 定时任务日志信息
     * @param page mybatis-plus 分页对象
     * @return 定时任务日志集合
     */
    IPage<QuartzLog> selectPageBySelective(QuartzLog record, Page page);
    /**
     * 根据主键集合,批量查询定时任务日志列表
     *
     * @param ids 定时任务日志主键List集合
     * @return 定时任务日志集合
     */
    List<QuartzLog> selectByPrimaryKeys(List<Long> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 定时任务日志 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(QuartzLog record);


    /**
     * 插入单条数据
     *
     * @param record 定时任务日志 信息
     * @return 插入数量
     */
    int insert(QuartzLog record);
    /**
     * 条件插入单条数据
     *
     * @param record 定时任务日志 信息
     * @return 插入数量
     */
    int insertSelective(QuartzLog record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 定时任务日志集合
     * @return 插入数量
     */
    int insertBatch(List<QuartzLog> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 定时任务日志 信息
     * @return 修改数量
     */
    int updateByPrimaryKey(QuartzLog record);
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 定时任务日志 信息
     * @return 修改数量
     */
    int updateByPrimaryKeySelective(QuartzLog record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 定时任务日志 集合
     * @return 修改数量
     */
    int updateBatch(List<QuartzLog> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 定时任务日志 集合
     * @return 修改数量
     */
    int updateBatchSelective(List<QuartzLog> recordList);
    /**
     * 根据主键删除数据
     *
     * @param id 定时任务日志 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 根据主键集合删除数据
     *
     * @param ids 定时任务日志 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Long> ids);

}
