package com.zc.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.generator.entity.GenConfig;

import java.util.List;

/**
 * 代码生成器配置 服务层
 *
 * @author Zhang C
 * @date 2021-08-20
 */
public interface GenConfigService extends IService<GenConfig> {
    /**
     * 查询代码生成器配置信息
     *
     * @param id 代码生成器配置ID
     * @return 代码生成器配置信息
     */
    GenConfig selectByPrimaryKey(Long id);


    /**
     * 根据条件,查询代码生成器配置列表
     *
     * @param record 代码生成器配置信息
     * @return 代码生成器配置集合
     */
    List<GenConfig> selectListBySelective(GenConfig record);

    /**
     * 根据条件,分页查询代码生成器配置列表
     *
     * @param record 代码生成器配置信息
     * @param page mybatis-plus 分页对象
     * @return 代码生成器配置集合
     */
    IPage<GenConfig> selectPageBySelective(GenConfig record, Page page);
    /**
     * 根据主键集合,批量查询代码生成器配置列表
     *
     * @param ids 代码生成器配置主键List集合
     * @return 代码生成器配置集合
     */
    List<GenConfig> selectByPrimaryKeys(List<Long> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 代码生成器配置 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(GenConfig record);


    /**
     * 插入单条数据
     *
     * @param record 代码生成器配置 信息
     * @return 插入数量
     */
    int insert(GenConfig record);
    /**
     * 条件插入单条数据
     *
     * @param record 代码生成器配置 信息
     * @return 插入数量
     */
    int insertSelective(GenConfig record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 代码生成器配置集合
     * @return 插入数量
     */
    int insertBatch(List<GenConfig> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 代码生成器配置 信息
     * @return 修改数量
     */
    int update(GenConfig record);
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 代码生成器配置 信息
     * @return 修改数量
     */
    int updateBySelective(GenConfig record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 代码生成器配置 集合
     * @return 修改数量
     */
    int updateBatch(List<GenConfig> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 代码生成器配置 集合
     * @return 修改数量
     */
    int updateBatchBySelective(List<GenConfig> recordList);
    /**
     * 根据主键删除数据
     *
     * @param id 代码生成器配置 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 根据主键集合删除数据
     *
     * @param ids 代码生成器配置 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Long> ids);

}
