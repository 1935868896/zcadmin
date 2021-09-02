package com.zc.modules.system.service;

import com.zc.modules.system.entity.Menu;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.modules.system.vo.MenuVO;

import java.util.List;

/**
 * 系统菜单 服务层
 *
 * @author zhangc
 * @date 2021-09-01
 */
public interface MenuService extends IService<Menu> {


    List<MenuVO> selectMenuVO();
    /**
     * 查询系统菜单信息
     *
     * @param id 系统菜单ID
     * @return 系统菜单信息
     */
        Menu selectByPrimaryKey(Long id);


    /**
     * 根据条件,查询系统菜单列表
     *
     * @param record 系统菜单信息
     * @return 系统菜单集合
     */
    List<Menu> selectListBySelective(Menu record);

    /**
     * 根据条件,查询第一个系统菜单对象(一般用于条件可以确定唯一数据)
     *
     * @param record 系统菜单信息
     * @return 系统菜单
     */
        Menu selectOneBySelective(Menu record);

    /**
     * 根据条件,分页查询系统菜单列表
     *
     * @param record 系统菜单信息
     * @param page mybatis-plus 分页对象
     * @return 系统菜单集合
     */
    IPage<Menu> selectPageBySelective(Menu record, Page page);

    /**
     * 根据主键集合,批量查询系统菜单列表
     *
     * @param ids 系统菜单主键List集合
     * @return 系统菜单集合
     */
    List<Menu> selectByPrimaryKeys(List<Long> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 系统菜单 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(Menu record);


    /**
     * 插入单条数据
     *
     * @param record 系统菜单 信息
     * @return 插入数量
     */
    int insert(Menu record);

    /**
     * 条件插入单条数据
     *
     * @param record 系统菜单 信息
     * @return 插入数量
     */
    int insertSelective(Menu record);

    /**
     * 批量插入多条数据
     *
     * @param recordList 系统菜单集合
     * @return 插入数量
     */
    int insertBatch(List<Menu> recordList);

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 系统菜单 信息
     * @return 修改数量
     */
    int update(Menu record);

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 系统菜单 信息
     * @return 修改数量
     */
    int updateSelective(Menu record);

    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
     *
    * @param params  需要修改的信息
    * @param record  根据的条件信息
    *  @return 修改数量
    */
    int updateParamsBySelective(Menu params,Menu record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 系统菜单 集合
     * @return 修改数量
     */
    int updateBatch(List<Menu> recordList);

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 系统菜单 集合
     * @return 修改数量
     */
    int updateBatchBySelective(List<Menu> recordList);

    /**
     * 根据条件删除数据
     *
    * @param record  删除的条件
    * @return 删除数量
     */
    int deleteBySelective(Menu record);

    /**
     * 根据主键删除数据
     *
     * @param id 系统菜单 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据主键集合删除数据
     *
     * @param ids 系统菜单 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Long> ids);

}
