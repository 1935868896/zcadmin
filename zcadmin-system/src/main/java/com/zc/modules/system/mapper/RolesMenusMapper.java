package com.zc.modules.system.mapper;

import com.zc.modules.system.entity.RolesMenus;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色菜单关联 数据层
 *
 * @author zhangc
 * @date 2021-09-01
 */
@Mapper
public interface RolesMenusMapper extends BaseMapper<RolesMenus> {

    /**
     * 查询角色菜单关联信息
     *
     * @param id 角色菜单关联ID
     * @return 角色菜单关联信息
     */
    RolesMenus selectByPrimaryKey(Long id);


    /**
     * 根据条件,查询角色菜单关联列表
     *
     * @param record 角色菜单关联信息
     * @return 角色菜单关联集合
     */
    List<RolesMenus> selectListBySelective(RolesMenus record);

    /**
     * 根据条件,查询第一个角色菜单关联对象(一般用于条件可以确定唯一数据)
     *
     * @param record 角色菜单关联信息
     * @return 角色菜单关联
     */
     RolesMenus selectOneBySelective(RolesMenus record);

    /**
     * 根据条件,分页查询角色菜单关联列表
     *
     * @param record 角色菜单关联信息
     * @param page mybatis-plus 分页对象
     * @return 角色菜单关联集合
     */
    IPage<RolesMenus> selectPageBySelective(RolesMenus record, Page page);
    /**
     * 根据主键集合,批量查询角色菜单关联列表
     *
     * @param ids 角色菜单关联主键List集合
     * @return 角色菜单关联集合
     */
    List<RolesMenus> selectByPrimaryKeys(List<Long> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 角色菜单关联 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(RolesMenus record);


    /**
     * 插入单条数据
     *
     * @param record 角色菜单关联 信息
     * @return 插入数量
     */
    int insert(RolesMenus record);
    /**
     * 条件插入单条数据
     *
     * @param record 角色菜单关联 信息
     * @return 插入数量
     */
    int insertSelective(RolesMenus record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 角色菜单关联集合
     * @return 插入数量
     */
    int insertBatch(List<RolesMenus> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 角色菜单关联 信息
     * @return 修改数量
     */
    int update(RolesMenus record);
    /**
     * 根据主键修改单条数据,仅修改存在数值的属性
     *
     * @param record 角色菜单关联 信息
     * @return 修改数量
     */
    int updateSelective(RolesMenus record);

    /**
     * 根据条件修改某些参数,仅修改存在数值的属性
     *
     * @param params  需要修改的信息
     * @param record  根据的条件信息
     * @return 修改数量
     */
    int updateParamsBySelective(RolesMenus params,RolesMenus record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 角色菜单关联 集合
     * @return 修改数量
     */
    int updateBatch(List<RolesMenus> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 角色菜单关联 集合
     * @return 修改数量
     */
    int updateBatchSelective(List<RolesMenus> recordList);
    /**
     * 根据条件删除数据
     *
     * @param record  删除的条件
     * @return 删除数量
     */
    int deleteBySelective(RolesMenus record);
    /**
     * 根据数据值删除数据
     *
     * @param id 角色菜单关联 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Long id);


    /**
     * 根据主键集合删除数据
     *
     * @param ids 角色菜单关联 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Long> ids);

}
