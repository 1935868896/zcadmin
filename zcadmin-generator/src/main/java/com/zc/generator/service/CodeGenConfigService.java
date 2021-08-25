package com.zc.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.generator.entity.CodeGenConfig;

import java.util.List;

/**
 * 代码生成器配置 服务层
 *
 * @author Zhang C
 * @date 2021-08-20
 */
public interface CodeGenConfigService extends IService<CodeGenConfig> {
    /**
     * 查询代码生成器配置信息
     *
     * @param id 代码生成器配置ID
     * @return 代码生成器配置信息
     */
    CodeGenConfig selectByPrimaryKey(Long id);

    CodeGenConfig selectOneBySelective(CodeGenConfig record);


    /**
     * 根据条件,查询代码生成器配置列表
     *
     * @param record 代码生成器配置信息
     * @return 代码生成器配置集合
     */
    List<CodeGenConfig> selectListBySelective(CodeGenConfig record);

    /**
     * 根据条件,分页查询代码生成器配置列表
     *
     * @param record 代码生成器配置信息
     * @param page mybatis-plus 分页对象
     * @return 代码生成器配置集合
     */
    IPage<CodeGenConfig> selectPageBySelective(CodeGenConfig record, Page page);
    /**
     * 根据主键集合,批量查询代码生成器配置列表
     *
     * @param ids 代码生成器配置主键List集合
     * @return 代码生成器配置集合
     */
    List<CodeGenConfig> selectByPrimaryKeys(List<Long> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 代码生成器配置 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(CodeGenConfig record);


    /**
     * 插入单条数据
     *
     * @param record 代码生成器配置 信息
     * @return 插入数量
     */
    int insert(CodeGenConfig record);
    /**
     * 条件插入单条数据
     *
     * @param record 代码生成器配置 信息
     * @return 插入数量
     */
    int insertSelective(CodeGenConfig record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 代码生成器配置集合
     * @return 插入数量
     */
    int insertBatch(List<CodeGenConfig> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 代码生成器配置 信息
     * @return 修改数量
     */
    int update(CodeGenConfig record);
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 代码生成器配置 信息
     * @return 修改数量
     */
    int updateBySelective(CodeGenConfig record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 代码生成器配置 集合
     * @return 修改数量
     */
    int updateBatch(List<CodeGenConfig> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 代码生成器配置 集合
     * @return 修改数量
     */
    int updateBatchBySelective(List<CodeGenConfig> recordList);
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
