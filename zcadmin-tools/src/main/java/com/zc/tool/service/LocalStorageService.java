package com.zc.tool.service;

import com.zc.tool.entity.LocalStorage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 本地存储 服务层
 *
 * @author zhangc
 * @date 2021-09-03
 */
public interface LocalStorageService extends IService<LocalStorage> {
    /**
     * 查询本地存储信息
     *
     * @param id 本地存储ID
     * @return 本地存储信息
     */
        LocalStorage selectByPrimaryKey(Long id);


    /**
     * 根据条件,查询本地存储列表
     *
     * @param record 本地存储信息
     * @return 本地存储集合
     */
    List<LocalStorage> selectListBySelective(LocalStorage record);

    /**
     * 根据条件,查询第一个本地存储对象(一般用于条件可以确定唯一数据)
     *
     * @param record 本地存储信息
     * @return 本地存储
     */
        LocalStorage selectOneBySelective(LocalStorage record);

    /**
     * 根据条件,分页查询本地存储列表
     *
     * @param record 本地存储信息
     * @param page mybatis-plus 分页对象
     * @return 本地存储集合
     */
    IPage<LocalStorage> selectPageBySelective(LocalStorage record, Page page);

    /**
     * 根据主键集合,批量查询本地存储列表
     *
     * @param ids 本地存储主键List集合
     * @return 本地存储集合
     */
    List<LocalStorage> selectByPrimaryKeys(List<Long> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 本地存储 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(LocalStorage record);


    /**
     * 插入单条数据
     *
     * @param record 本地存储 信息
     * @return 插入数量
     */
    int insert(LocalStorage record);

    /**
     * 条件插入单条数据
     *
     * @param record 本地存储 信息
     * @return 插入数量
     */
    int insertSelective(LocalStorage record);

    /**
     * 批量插入多条数据
     *
     * @param recordList 本地存储集合
     * @return 插入数量
     */
    int insertBatch(List<LocalStorage> recordList);

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 本地存储 信息
     * @return 修改数量
     */
    int update(LocalStorage record);

    int selectCountName(LocalStorage record);

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 本地存储 信息
     * @return 修改数量
     */
    int updateSelective(LocalStorage record);

    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
     *
    * @param params  需要修改的信息
    * @param record  根据的条件信息
    *  @return 修改数量
    */
    int updateParamsBySelective(LocalStorage params,LocalStorage record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 本地存储 集合
     * @return 修改数量
     */
    int updateBatch(List<LocalStorage> recordList);

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 本地存储 集合
     * @return 修改数量
     */
    int updateBatchBySelective(List<LocalStorage> recordList);

    /**
     * 根据条件删除数据
     *
    * @param record  删除的条件
    * @return 删除数量
     */
    int deleteBySelective(LocalStorage record);

    /**
     * 根据主键删除数据
     *
     * @param id 本地存储 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据主键集合删除数据
     *
     * @param ids 本地存储 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Long> ids);

}
