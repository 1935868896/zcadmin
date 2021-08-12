package com.zc.modules.quartz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.quartz.mapper.QuartzLogMapper;
import com.zc.modules.quartz.entity.QuartzLog;
import com.zc.modules.quartz.service.QuartzLogService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 定时任务日志 服务层实现
 *
 * @author Zhang C
 * @date 2021-08-12
 */
@Service
@RequiredArgsConstructor
public class QuartzLogServiceImpl extends ServiceImpl<QuartzLogMapper, QuartzLog> implements QuartzLogService {

    private final QuartzLogMapper quartzLogMapper;

    /**
     * 查询定时任务日志信息
     *
     * @param id 定时任务日志ID
     * @return 定时任务日志信息
     */
    @Override
    public QuartzLog selectByPrimaryKey(Long id) {
        return quartzLogMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询定时任务日志列表
     *
     * @param record 定时任务日志信息
     * @return 定时任务日志集合
     */
    @Override
    public List<QuartzLog> selectListBySelective(QuartzLog record) {
        return quartzLogMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,分页查询定时任务日志列表
     *
     * @param record 定时任务日志信息
     * @param page mybatis-plus 分页对象
     * @return 定时任务日志集合
     */
    @Override
    public IPage<QuartzLog> selectPageBySelective(QuartzLog record, Page page) {
        return quartzLogMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询定时任务日志列表
     *
     * @param ids 定时任务日志主键List集合
     * @return 定时任务日志集合
     */
    @Override
    public List<QuartzLog> selectByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return quartzLogMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 定时任务日志 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(QuartzLog record) {
        return quartzLogMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 定时任务日志 信息
     * @return 插入数量
     */
    @Override
    public int insert(QuartzLog record) {
        return quartzLogMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 定时任务日志 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(QuartzLog record) {
        return quartzLogMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 定时任务日志集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<QuartzLog> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<QuartzLog>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<QuartzLog> records : list) {
            int count = quartzLogMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 定时任务日志 信息
     * @return 修改数量
     */
    @Override
    public int updateByPrimaryKey(QuartzLog record) {
        return quartzLogMapper.updateByPrimaryKey(record);
    }
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 定时任务日志 信息
     * @return 修改数量
     */
    @Override
    public int updateByPrimaryKeySelective(QuartzLog record) {
        return quartzLogMapper.updateByPrimaryKeySelective(record);
    }
    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 定时任务日志 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<QuartzLog> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<QuartzLog>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<QuartzLog> records : list) {
            int count = quartzLogMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 定时任务日志 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchSelective(List<QuartzLog> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<QuartzLog>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<QuartzLog> records : listLists) {
            int count = quartzLogMapper.updateBatchSelective(records);
            result = result + count;
        }
        return result;
    }
    /**
     * 根据主键删除数据
     *
     * @param id 定时任务日志 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return quartzLogMapper.deleteByPrimaryKey(id);
    }
    /**
     * 根据主键集合删除数据
     *
     * @param ids 定时任务日志 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return quartzLogMapper.deleteByPrimaryKeys(ids);
    }

}
