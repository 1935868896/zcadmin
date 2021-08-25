package com.zc.generator.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.generator.entity.CodeColumnConfig;

import java.util.List;

/**
 * 代码生成字段存储 服务层
 *
 * @author Zhang C
 * @date 2021-08-20
 */
public interface CodeColumnConfigService extends IService<CodeColumnConfig> {
    /**
     * 查询代码生成字段存储信息
     *
     * @param id 代码生成字段存储ID
     * @return 代码生成字段存储信息
     */
    CodeColumnConfig selectByPrimaryKey(Long id);


    /**
     * 根据条件,查询代码生成字段存储列表
     *
     * @param record 代码生成字段存储信息
     * @return 代码生成字段存储集合
     */
    List<CodeColumnConfig> selectListBySelective(CodeColumnConfig record);

    /**
     * 根据条件,分页查询代码生成字段存储列表
     *
     * @param record 代码生成字段存储信息
     * @param page mybatis-plus 分页对象
     * @return 代码生成字段存储集合
     */
    IPage<CodeColumnConfig> selectPageBySelective(CodeColumnConfig record, Page page);
    /**
     * 根据主键集合,批量查询代码生成字段存储列表
     *
     * @param ids 代码生成字段存储主键List集合
     * @return 代码生成字段存储集合
     */
    List<CodeColumnConfig> selectByPrimaryKeys(List<Long> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 代码生成字段存储 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(CodeColumnConfig record);


    /**
     * 插入单条数据
     *
     * @param record 代码生成字段存储 信息
     * @return 插入数量
     */
    int insert(CodeColumnConfig record);
    /**
     * 条件插入单条数据
     *
     * @param record 代码生成字段存储 信息
     * @return 插入数量
     */
    int insertSelective(CodeColumnConfig record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 代码生成字段存储集合
     * @return 插入数量
     */
    int insertBatch(List<CodeColumnConfig> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 代码生成字段存储 信息
     * @return 修改数量
     */
    int update(CodeColumnConfig record);
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 代码生成字段存储 信息
     * @return 修改数量
     */
    int updateBySelective(CodeColumnConfig record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 代码生成字段存储 集合
     * @return 修改数量
     */
    int updateBatch(List<CodeColumnConfig> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 代码生成字段存储 集合
     * @return 修改数量
     */
    int updateBatchBySelective(List<CodeColumnConfig> recordList);
    /**
     * 根据主键删除数据
     *
     * @param id 代码生成字段存储 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 根据主键集合删除数据
     *
     * @param ids 代码生成字段存储 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Long> ids);

}
