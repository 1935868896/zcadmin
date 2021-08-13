package com.zc.modules.quartz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.exception.BadRequestException;
import com.zc.modules.quartz.QuartzScheduleHandle;
import com.zc.modules.quartz.entity.QuartzJob;
import com.zc.modules.quartz.mapper.QuartzJobMapper;
import com.zc.modules.quartz.service.QuartzJobService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 定时任务 服务层实现
 *
 * @author Zhang C
 * @date 2021-08-12
 */
@Service
@RequiredArgsConstructor
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobMapper, QuartzJob> implements QuartzJobService {

    private final QuartzJobMapper quartzJobMapper;
    private final QuartzScheduleHandle quartzScheduleHandle;







    /**
     * 查询定时任务信息
     *
     * @param id 定时任务ID
     * @return 定时任务信息
     */
    @Override
    public QuartzJob selectByPrimaryKey(Long id) {
        return quartzJobMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询定时任务列表
     *
     * @param record 定时任务信息
     * @return 定时任务集合
     */
    @Override
    public List<QuartzJob> selectListBySelective(QuartzJob record) {
        return quartzJobMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,分页查询定时任务列表
     *
     * @param record 定时任务信息
     * @param page mybatis-plus 分页对象
     * @return 定时任务集合
     */
    @Override
    public IPage<QuartzJob> selectPageBySelective(QuartzJob record, Page page) {
        return quartzJobMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询定时任务列表
     *
     * @param ids 定时任务主键List集合
     * @return 定时任务集合
     */
    @Override
    public List<QuartzJob> selectByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return quartzJobMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 定时任务 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(QuartzJob record) {
        return quartzJobMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 定时任务 信息
     * @return 插入数量
     */
    @Override
    public int insert(QuartzJob record) {
        return quartzJobMapper.insert(record);
    }

    @Override
    public int create(QuartzJob record) {
        int insert = quartzJobMapper.insert(record);
        quartzScheduleHandle.createJob(record);
        return insert;
    }

    @Override
    public void pause(QuartzJob record) {
        if (record.getJobId()==null){
            throw new BadRequestException("不存在JobId");
        }
        quartzScheduleHandle.pauseJob(record);
        quartzJobMapper.updateByPrimaryKeySelective(
                QuartzJob.builder().jobId(record.getJobId()).isPause(true).build()
        );
    }

    @Override
    public void pauseAll(QuartzJob record) {

    }

    @Override
    public void resume(QuartzJob record) {
        if (record.getJobId()==null){
            throw new BadRequestException("不存在JobId");
        }
        quartzScheduleHandle.resumeJob(record);
        quartzJobMapper.updateByPrimaryKeySelective(
                QuartzJob.builder().jobId(record.getJobId()).isPause(false).build()
        );
    }

    @Override
    public void resumeAll(QuartzJob record) {

    }

    @Override
    public void update(QuartzJob record) {
        if (record.getJobId()==null){
            throw new BadRequestException("不存在JobId");
        }
        quartzScheduleHandle.modifyJob(record);
        quartzJobMapper.updateByPrimaryKey(record);
    }

    @Override
    public void delete(QuartzJob record) {
        if (record.getJobId()==null){
            throw new BadRequestException("不存在JobId");
        }
        quartzScheduleHandle.deleteJob(record);
        quartzJobMapper.deleteByPrimaryKey(record.getJobId());
    }

    @Override
    public void runNow(QuartzJob record) {
        if (record.getJobId()==null){
            throw new BadRequestException("不存在JobId");
        }
        quartzScheduleHandle.runJobNow(record);

    }

    /**
     * 条件插入单条数据
     *
     * @param record 定时任务 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(QuartzJob record) {
        return quartzJobMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 定时任务集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<QuartzJob> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<QuartzJob>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<QuartzJob> records : list) {
            int count = quartzJobMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 定时任务 信息
     * @return 修改数量
     */
    @Override
    public int updateByPrimaryKey(QuartzJob record) {
        return quartzJobMapper.updateByPrimaryKey(record);
    }
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 定时任务 信息
     * @return 修改数量
     */
    @Override
    public int updateByPrimaryKeySelective(QuartzJob record) {
        return quartzJobMapper.updateByPrimaryKeySelective(record);
    }
    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 定时任务 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<QuartzJob> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<QuartzJob>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<QuartzJob> records : list) {
            int count = quartzJobMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 定时任务 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchSelective(List<QuartzJob> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<QuartzJob>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<QuartzJob> records : listLists) {
            int count = quartzJobMapper.updateBatchSelective(records);
            result = result + count;
        }
        return result;
    }
    /**
     * 根据主键删除数据
     *
     * @param id 定时任务 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return quartzJobMapper.deleteByPrimaryKey(id);
    }
    /**
     * 根据主键集合删除数据
     *
     * @param ids 定时任务 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return quartzJobMapper.deleteByPrimaryKeys(ids);
    }

}
