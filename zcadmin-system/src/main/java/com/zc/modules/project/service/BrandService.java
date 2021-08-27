package com.zc.modules.project.service;

import com.zc.modules.project.entity.Brand;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 品牌 服务层
 *
 * @author zhangc
 * @date 2021-08-25
 */
public interface BrandService extends IService<Brand> {
    /**
     * 查询品牌信息
     *
     * @param id 品牌ID
     * @return 品牌信息
     */
    Brand selectByPrimaryKey(Long id);


    /**
     * 根据条件,查询品牌列表
     *
     * @param record 品牌信息
     * @return 品牌集合
     */
    List<Brand> selectListBySelective(Brand record);

    /**
     * 根据条件,查询第一个品牌对象(一般用于条件可以确定唯一数据)
     *
     * @param record 品牌信息
     * @return 品牌
     */
    Brand selectOneBySelective(Brand record);

    /**
     * 根据条件,分页查询品牌列表
     *
     * @param record 品牌信息
     * @param page mybatis-plus 分页对象
     * @return 品牌集合
     */
    IPage<Brand> selectPageBySelective(Brand record, Page page);
    /**
     * 根据主键集合,批量查询品牌列表
     *
     * @param ids 品牌主键List集合
     * @return 品牌集合
     */
    List<Brand> selectByPrimaryKeys(List<Long> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 品牌 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(Brand record);


    /**
     * 插入单条数据
     *
     * @param record 品牌 信息
     * @return 插入数量
     */
    int insert(Brand record);
    /**
     * 条件插入单条数据
     *
     * @param record 品牌 信息
     * @return 插入数量
     */
    int insertSelective(Brand record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 品牌集合
     * @return 插入数量
     */
    int insertBatch(List<Brand> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 品牌 信息
     * @return 修改数量
     */
    int update(Brand record);
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 品牌 信息
     * @return 修改数量
     */
    int updateBySelective(Brand record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 品牌 集合
     * @return 修改数量
     */
    int updateBatch(List<Brand> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 品牌 集合
     * @return 修改数量
     */
    int updateBatchBySelective(List<Brand> recordList);
    /**
     * 根据主键删除数据
     *
     * @param id 品牌 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 根据主键集合删除数据
     *
     * @param ids 品牌 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Long> ids);

}
