package com.zc.modules.system.mapper;

import com.zc.modules.system.entity.Role;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色 数据层
 *
 * @author zhangc
 * @date 2021-09-01
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询角色信息
     *
     * @param id 角色ID
     * @return 角色信息
     */
    Role selectByPrimaryKey(Long id);


    /**
     * 根据条件,查询角色列表
     *
     * @param record 角色信息
     * @return 角色集合
     */
    List<Role> selectListBySelective(Role record);

    /**
     * 根据条件,查询第一个角色对象(一般用于条件可以确定唯一数据)
     *
     * @param record 角色信息
     * @return 角色
     */
     Role selectOneBySelective(Role record);

    /**
     * 根据条件,分页查询角色列表
     *
     * @param record 角色信息
     * @param page mybatis-plus 分页对象
     * @return 角色集合
     */
    IPage<Role> selectPageBySelective(Role record, Page page);
    /**
     * 根据主键集合,批量查询角色列表
     *
     * @param ids 角色主键List集合
     * @return 角色集合
     */
    List<Role> selectByPrimaryKeys(List<Long> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 角色 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(Role record);


    /**
     * 插入单条数据
     *
     * @param record 角色 信息
     * @return 插入数量
     */
    int insert(Role record);
    /**
     * 条件插入单条数据
     *
     * @param record 角色 信息
     * @return 插入数量
     */
    int insertSelective(Role record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 角色集合
     * @return 插入数量
     */
    int insertBatch(List<Role> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 角色 信息
     * @return 修改数量
     */
    int update(Role record);
    /**
     * 根据主键修改单条数据,仅修改存在数值的属性
     *
     * @param record 角色 信息
     * @return 修改数量
     */
    int updateSelective(Role record);

    /**
     * 根据条件修改某些参数,仅修改存在数值的属性
     *
     * @param params  需要修改的信息
     * @param record  根据的条件信息
     * @return 修改数量
     */
    int updateParamsBySelective(Role params,Role record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 角色 集合
     * @return 修改数量
     */
    int updateBatch(List<Role> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 角色 集合
     * @return 修改数量
     */
    int updateBatchSelective(List<Role> recordList);
    /**
     * 根据条件删除数据
     *
     * @param record  删除的条件
     * @return 删除数量
     */
    int deleteBySelective(Role record);
    /**
     * 根据数据值删除数据
     *
     * @param id 角色 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Long id);


    /**
     * 根据主键集合删除数据
     *
     * @param ids 角色 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Long> ids);

}
