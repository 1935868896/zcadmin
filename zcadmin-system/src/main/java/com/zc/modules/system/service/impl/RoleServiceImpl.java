package com.zc.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.system.mapper.RoleMapper;
import com.zc.modules.system.entity.Role;
import com.zc.modules.system.service.RoleService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色 服务层实现
 *
 * @author zhangc
 * @date 2021-09-01
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMapper roleMapper;

    /**
     * 查询角色信息
     *
     * @param id 角色ID
     * @return 角色信息
     */
    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询角色列表
     *
     * @param record 角色信息
     * @return 角色集合
     */
    @Override
    public List<Role> selectListBySelective(Role record) {
        return roleMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个角色对象(一般用于条件可以确定唯一数据)
     *
     * @param record 角色信息
     * @return 角色
     */
    @Override
    public Role selectOneBySelective(Role record) {
        return roleMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询角色列表
     *
     * @param record 角色信息
     * @param page mybatis-plus 分页对象
     * @return 角色集合
     */
    @Override
    public IPage<Role> selectPageBySelective(Role record, Page page) {
        return roleMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询角色列表
     *
     * @param ids 角色主键List集合
     * @return 角色集合
     */
    @Override
    public List<Role> selectByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return roleMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 角色 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(Role record) {
        return roleMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 角色 信息
     * @return 插入数量
     */
    @Override
    public int insert(Role record) {
        return roleMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 角色 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(Role record) {
        return roleMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 角色集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<Role> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<Role>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Role> records : list) {
            int count = roleMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 角色 信息
     * @return 修改数量
     */
    @Override
    public int update(Role record) {
        return roleMapper.update(record);
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 角色 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(Role record) {
        return roleMapper.updateSelective(record);
    }


    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
    *
    * @param  params  需要修改的信息
    * @param  record  根据的条件信息
    * @return 修改数量
    */
    @Override
    public int updateParamsBySelective(Role params,Role record) {
        return roleMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 角色 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<Role> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<Role>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Role> records : list) {
            int count = roleMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 角色 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<Role> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<Role>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Role> records : listLists) {
            int count = roleMapper.updateBatchSelective(records);
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
    public int deleteBySelective(Role record){
        return roleMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 角色 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 角色 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return roleMapper.deleteByPrimaryKeys(ids);
    }

}
