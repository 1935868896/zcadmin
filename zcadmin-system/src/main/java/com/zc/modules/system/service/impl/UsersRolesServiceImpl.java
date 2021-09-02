package com.zc.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.system.mapper.UsersRolesMapper;
import com.zc.modules.system.entity.UsersRoles;
import com.zc.modules.system.service.UsersRolesService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户角色关联 服务层实现
 *
 * @author zhangc
 * @date 2021-09-01
 */
@Service
@RequiredArgsConstructor
public class UsersRolesServiceImpl extends ServiceImpl<UsersRolesMapper, UsersRoles> implements UsersRolesService {

    private final UsersRolesMapper usersRolesMapper;

    /**
     * 查询用户角色关联信息
     *
     * @param id 用户角色关联ID
     * @return 用户角色关联信息
     */
    @Override
    public UsersRoles selectByPrimaryKey(Long id) {
        return usersRolesMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询用户角色关联列表
     *
     * @param record 用户角色关联信息
     * @return 用户角色关联集合
     */
    @Override
    public List<UsersRoles> selectListBySelective(UsersRoles record) {
        return usersRolesMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个用户角色关联对象(一般用于条件可以确定唯一数据)
     *
     * @param record 用户角色关联信息
     * @return 用户角色关联
     */
    @Override
    public UsersRoles selectOneBySelective(UsersRoles record) {
        return usersRolesMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询用户角色关联列表
     *
     * @param record 用户角色关联信息
     * @param page mybatis-plus 分页对象
     * @return 用户角色关联集合
     */
    @Override
    public IPage<UsersRoles> selectPageBySelective(UsersRoles record, Page page) {
        return usersRolesMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询用户角色关联列表
     *
     * @param ids 用户角色关联主键List集合
     * @return 用户角色关联集合
     */
    @Override
    public List<UsersRoles> selectByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return usersRolesMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 用户角色关联 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(UsersRoles record) {
        return usersRolesMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 用户角色关联 信息
     * @return 插入数量
     */
    @Override
    public int insert(UsersRoles record) {
        return usersRolesMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 用户角色关联 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(UsersRoles record) {
        return usersRolesMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 用户角色关联集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<UsersRoles> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<UsersRoles>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<UsersRoles> records : list) {
            int count = usersRolesMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 用户角色关联 信息
     * @return 修改数量
     */
    @Override
    public int update(UsersRoles record) {
        return usersRolesMapper.update(record);
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 用户角色关联 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(UsersRoles record) {
        return usersRolesMapper.updateSelective(record);
    }


    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
    *
    * @param  params  需要修改的信息
    * @param  record  根据的条件信息
    * @return 修改数量
    */
    @Override
    public int updateParamsBySelective(UsersRoles params,UsersRoles record) {
        return usersRolesMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 用户角色关联 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<UsersRoles> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<UsersRoles>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<UsersRoles> records : list) {
            int count = usersRolesMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 用户角色关联 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<UsersRoles> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<UsersRoles>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<UsersRoles> records : listLists) {
            int count = usersRolesMapper.updateBatchSelective(records);
            result = result + count;
        }
        return result;
    }

    /**
    * 根据条件删除数据
    *
    * @param record  删除的条件
    * @return 删除数量
    */
    public int deleteBySelective(UsersRoles record){
        return usersRolesMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 用户角色关联 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return usersRolesMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 用户角色关联 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return usersRolesMapper.deleteByPrimaryKeys(ids);
    }

}
