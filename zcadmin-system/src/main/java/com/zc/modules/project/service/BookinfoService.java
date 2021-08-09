package com.zc.modules.project.service;

import com.zc.modules.project.entity.Bookinfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 图书 服务层
 *
 * @author ruoyi
 * @date 2021-08-09
 */
public interface BookinfoService extends IService<Bookinfo> {
    /**
     * 查询图书信息
     *
     * @param id 图书ID
     * @return 图书信息
     */
    Bookinfo selectByPrimaryKey(Integer id);


    /**
     * 根据条件,查询图书列表
     *
     * @param record 图书信息
     * @return 图书集合
     */
    List<Bookinfo> selectListBySelective(Bookinfo record);

    /**
     * 根据条件,分页查询图书列表
     *
     * @param record 图书信息
     * @param page mybatis-plus 分页对象
     * @return 图书集合
     */
    IPage<Bookinfo> selectPageBySelective(Bookinfo record, Page page);
    /**
     * 根据主键集合,批量查询图书列表
     *
     * @param ids 图书主键List集合
     * @return 图书集合
     */
    List<Bookinfo> selectByPrimaryKeys(List<Integer> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 图书 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(Bookinfo record);


    /**
     * 插入单条数据
     *
     * @param record 图书 信息
     * @return 插入数量
     */
    int insert(Bookinfo record);
    /**
     * 条件插入单条数据
     *
     * @param record 图书 信息
     * @return 插入数量
     */
    int insertSelective(Bookinfo record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 图书集合
     * @return 插入数量
     */
    int insertBatch(List<Bookinfo> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 图书 信息
     * @return 修改数量
     */
    int updateByPrimaryKey(Bookinfo record);
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 图书 信息
     * @return 修改数量
     */
    int updateByPrimaryKeySelective(Bookinfo record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 图书 集合
     * @return 修改数量
     */
    int updateBatch(List<Bookinfo> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 图书 集合
     * @return 修改数量
     */
    int updateBatchSelective(List<Bookinfo> recordList);
    /**
     * 根据主键删除数据
     *
     * @param id 图书 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 根据主键集合删除数据
     *
     * @param ids 图书 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Integer> ids);

}
