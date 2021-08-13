package com.zc.modules.quartz.mapper;

import com.zc.modules.quartz.entity.QuartzJob;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务 数据层
 *
 * @author Zhang C
 * @date 2021-08-12
 */
@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {




    int updateAllBySelective(QuartzJob record);

    int deleteAllBySelective(QuartzJob record);

    /**
     * 查询定时任务信息
     *
     * @param id 定时任务ID
     * @return 定时任务信息
     */
    QuartzJob selectByPrimaryKey(Long id);


    /**
     * 根据条件,查询定时任务列表
     *
     * @param record 定时任务信息
     * @return 定时任务集合
     */
    List<QuartzJob> selectListBySelective(QuartzJob record);

    /**
     * 根据条件,分页查询定时任务列表
     *
     * @param record 定时任务信息
     * @param page mybatis-plus 分页对象
     * @return 定时任务集合
     */
    IPage<QuartzJob> selectPageBySelective(QuartzJob record, Page page);
    /**
     * 根据主键集合,批量查询定时任务列表
     *
     * @param ids 定时任务主键List集合
     * @return 定时任务集合
     */
    List<QuartzJob> selectByPrimaryKeys(List<Long> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 定时任务 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(QuartzJob record);


    /**
     * 插入单条数据
     *
     * @param record 定时任务 信息
     * @return 插入数量
     */
    int insert(QuartzJob record);
    /**
     * 条件插入单条数据
     *
     * @param record 定时任务 信息
     * @return 插入数量
     */
    int insertSelective(QuartzJob record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 定时任务集合
     * @return 插入数量
     */
    int insertBatch(List<QuartzJob> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 定时任务 信息
     * @return 修改数量
     */
    int updateByPrimaryKey(QuartzJob record);
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 定时任务 信息
     * @return 修改数量
     */
    int updateByPrimaryKeySelective(QuartzJob record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 定时任务 集合
     * @return 修改数量
     */
    int updateBatch(List<QuartzJob> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 定时任务 集合
     * @return 修改数量
     */
    int updateBatchSelective(List<QuartzJob> recordList);
    /**
     * 根据主键删除数据
     *
     * @param id 定时任务 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 根据主键集合删除数据
     *
     * @param ids 定时任务 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Long> ids);

}