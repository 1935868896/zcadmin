package com.zc.modules.system.mapper;

import com.zc.entity.UserDto;
import com.zc.modules.system.entity.User;
import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 系统用户 数据层
 *
 * @author zhangc
 * @date 2021-09-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from sys_user where username=#{name}")
    UserDto selectUserByUserName(String name);

    @Select("select * from sys_user where username=#{name}")
    User selectSysUserByUserName(String name);

    @Select("SELECT  m.permission FROM sys_roles_menus AS rm " +
            "LEFT JOIN sys_menu AS m ON m.menu_id = rm.menu_id " +
            "LEFT JOIN  sys_role AS r ON r.role_id = rm.role_id " +
            "LEFT JOIN sys_users_roles AS ur ON ur.role_id = r.role_id " +
            "left join sys_user as u on u.user_id=ur.user_id " +
            "where username=#{username}")
    Set<String> selectPermissionByUsername(String username);

    @Select("SELECT NAME FROM sys_role AS sr\n" +
            "LEFT JOIN sys_users_roles AS sur ON sr.role_id = sur.role_id\n" +
            "LEFT JOIN sys_user AS su ON su.user_id = sur.user_id \n" +
            "WHERE su.username = #{username}")
    Set<String> selectRolesByUsername(String username);

    @Select("SELECT NAME FROM sys_role AS sr\n" +
            "LEFT JOIN sys_users_roles AS sur ON sr.role_id = sur.role_id\n" +
            "LEFT JOIN sys_user AS su ON su.user_id = sur.user_id \n" +
            "WHERE su.user_id = #{userId}")
    Set<String> selectRolesByUserId(Long userId);

    @Select("SELECT role_id FROM `sys_users_roles` where user_id=#{userId}")
    Set<Long> selectRolesIdByUserId(Long userId);



    /**
     * 查询系统用户信息
     *
     * @param id 系统用户ID
     * @return 系统用户信息
     */
    User selectByPrimaryKey(Long id);


    /**
     * 根据条件,查询系统用户列表
     *
     * @param record 系统用户信息
     * @return 系统用户集合
     */
    List<User> selectListBySelective(User record);

    /**
     * 根据条件,查询第一个系统用户对象(一般用于条件可以确定唯一数据)
     *
     * @param record 系统用户信息
     * @return 系统用户
     */
     User selectOneBySelective(User record);

    /**
     * 根据条件,分页查询系统用户列表
     *
     * @param record 系统用户信息
     * @param page mybatis-plus 分页对象
     * @return 系统用户集合
     */
    IPage<User> selectPageBySelective(User record, Page page);
    /**
     * 根据主键集合,批量查询系统用户列表
     *
     * @param ids 系统用户主键List集合
     * @return 系统用户集合
     */
    List<User> selectByPrimaryKeys(List<Long> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 系统用户 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(User record);


    /**
     * 插入单条数据
     *
     * @param record 系统用户 信息
     * @return 插入数量
     */
    int insert(User record);
    /**
     * 条件插入单条数据
     *
     * @param record 系统用户 信息
     * @return 插入数量
     */
    int insertSelective(User record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 系统用户集合
     * @return 插入数量
     */
    int insertBatch(List<User> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 系统用户 信息
     * @return 修改数量
     */
    int update(User record);
    /**
     * 根据主键修改单条数据,仅修改存在数值的属性
     *
     * @param record 系统用户 信息
     * @return 修改数量
     */
    int updateSelective(User record);

    /**
     * 根据条件修改某些参数,仅修改存在数值的属性
     *
     * @param params  需要修改的信息
     * @param record  根据的条件信息
     * @return 修改数量
     */
    int updateParamsBySelective(User params,User record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 系统用户 集合
     * @return 修改数量
     */
    int updateBatch(List<User> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 系统用户 集合
     * @return 修改数量
     */
    int updateBatchSelective(List<User> recordList);
    /**
     * 根据条件删除数据
     *
     * @param record  删除的条件
     * @return 删除数量
     */
    int deleteBySelective(User record);
    /**
     * 根据数据值删除数据
     *
     * @param id 系统用户 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Long id);


    /**
     * 根据主键集合删除数据
     *
     * @param ids 系统用户 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Long> ids);

}
