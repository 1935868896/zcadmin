package com.zc.modules.system.mapper;

import com.zc.modules.system.entity.SysDict;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典 数据层
 *
 * @author zhangc
 * @date 2021-10-08
 */
@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 查询数据字典信息
     *
     * @param id 数据字典ID
     * @return 数据字典信息
     */
    SysDict selectObjectById(Long id);


    /**
     * 根据条件,查询数据字典列表
     *
     * @param record 数据字典信息
     * @return 数据字典集合
     */
    List<SysDict> selectListByParam(SysDict record);

    /**
     * 根据条件,查询第一个数据字典对象(一般用于条件可以确定唯一数据)
     *
     * @param record 数据字典信息
     * @return 数据字典
     */
     SysDict selectOneByParam(SysDict record);
    /**
     * 根据条件,分页查询数据字典列表
     *
     * @param record 数据字典信息
     * @param page mybatis-plus 分页对象
     * @return 数据字典集合
     */
    IPage<SysDict> selectPageByParam(SysDict record, Page page);

    /**
     * 插入单条数据
     *
     * @param record 数据字典 信息
     * @return 插入数量
     */
    int insertOne(SysDict record);

        /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 数据字典 信息
     * @return 修改数量
     */
    int updateById(SysDict record);
    /**
     * 根据数据值删除数据
     *
     * @param id 数据字典 主键
     * @return 删除数量
     */
    int deleteById(Long id);
}
