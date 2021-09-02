package com.zc.modules.system.mapper;

import com.zc.modules.system.entity.UsersRoles;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关联 数据层
 *
 * @author zhangc
 * @date 2021-09-01
 */
@Mapper
public interface UsersRolesMapper extends BaseMapper<UsersRoles> {

    /**
     * 查询用户角色关联信息
     *
     * @param id 用户角色关联ID
     * @return 用户角色关联信息
     */
    UsersRoles selectByPrimaryKey(Long id);


    /**
     * 根据条件,查询用户角色关联列表
     *
     * @param record 用户角色关联信息
     * @return 用户角色关联集合
     */
    List<UsersRoles> selectListBySelective(UsersRoles record);

    /**
     * 根据条件,查询第一个用户角色关联对象(一般用于条件可以确定唯一数据)
     *
     * @param record 用户角色关联信息
     * @return 用户角色关联
     */
     UsersRoles selectOneBySelective(UsersRoles record);

    /**
     * 根据条件,分页查询用户角色关联列表
     *
     * @param record 用户角色关联信息
     * @param page mybatis-plus 分页对象
     * @return 用户角色关联集合
     */
    IPage<UsersRoles> selectPageBySelective(UsersRoles record, Page page);
    /**
     * 根据主键集合,批量查询用户角色关联列表
     *
     * @param ids 用户角色关联主键List集合
     * @return 用户角色关联集合
     */
    List<UsersRoles> selectByPrimaryKeys(List<Long> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 用户角色关联 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(UsersRoles record);


    /**
     * 插入单条数据
     *
     * @param record 用户角色关联 信息
     * @return 插入数量
     */
    int insert(UsersRoles record);
    /**
     * 条件插入单条数据
     *
     * @param record 用户角色关联 信息
     * @return 插入数量
     */
    int insertSelective(UsersRoles record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 用户角色关联集合
     * @return 插入数量
     */
    int insertBatch(List<UsersRoles> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 用户角色关联 信息
     * @return 修改数量
     */
    int update(UsersRoles record);
    /**
     * 根据主键修改单条数据,仅修改存在数值的属性
     *
     * @param record 用户角色关联 信息
     * @return 修改数量
     */
    int updateSelective(UsersRoles record);

    /**
     * 根据条件修改某些参数,仅修改存在数值的属性
     *
     * @param params  需要修改的信息
     * @param record  根据的条件信息
     * @return 修改数量
     */
    int updateParamsBySelective(UsersRoles params,UsersRoles record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 用户角色关联 集合
     * @return 修改数量
     */
    int updateBatch(List<UsersRoles> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 用户角色关联 集合
     * @return 修改数量
     */
    int updateBatchSelective(List<UsersRoles> recordList);
    /**
     * 根据条件删除数据
     *
     * @param record  删除的条件
     * @return 删除数量
     */
    int deleteBySelective(UsersRoles record);
    /**
     * 根据数据值删除数据
     *
     * @param id 用户角色关联 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Long id);


    /**
     * 根据主键集合删除数据
     *
     * @param ids 用户角色关联 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Long> ids);

}
