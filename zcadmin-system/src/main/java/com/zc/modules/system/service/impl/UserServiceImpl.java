package com.zc.modules.system.service.impl;

import java.util.List;
import java.util.Set;

import com.zc.modules.system.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.system.mapper.UserMapper;
import com.zc.modules.system.entity.User;
import com.zc.modules.system.service.UserService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统用户 服务层实现
 *
 * @author zhangc
 * @date 2021-09-01
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    /**
     * 查询系统用户信息
     *
     * @param id 系统用户ID
     * @return 系统用户信息
     */
    @Override
    public UserVO selectByPrimaryKey(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        Set<Long> roleIds = userMapper.selectRolesIdByUserId(user.getUserId());
        return UserVO.builder().user(user).roles(roleIds).build();
    }


    /**
     * 根据条件,查询系统用户列表
     *
     * @param record 系统用户信息
     * @return 系统用户集合
     */
    @Override
    public List<User> selectListBySelective(User record) {
        return userMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个系统用户对象(一般用于条件可以确定唯一数据)
     *
     * @param record 系统用户信息
     * @return 系统用户
     */
    @Override
    public User selectOneBySelective(User record) {
        return userMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询系统用户列表
     *
     * @param record 系统用户信息
     * @param page mybatis-plus 分页对象
     * @return 系统用户集合
     */
    @Override
    public IPage<User> selectPageBySelective(User record, Page page) {
        return userMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询系统用户列表
     *
     * @param ids 系统用户主键List集合
     * @return 系统用户集合
     */
    @Override
    public List<User> selectByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return userMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 系统用户 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(User record) {
        return userMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 系统用户 信息
     * @return 插入数量
     */
    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 系统用户 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 系统用户集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<User> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<User>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<User> records : list) {
            int count = userMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 系统用户 信息
     * @return 修改数量
     */
    @Override
    public int update(User record) {
        return userMapper.update(record);
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 系统用户 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(User record) {
        return userMapper.updateSelective(record);
    }


    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
    *
    * @param  params  需要修改的信息
    * @param  record  根据的条件信息
    * @return 修改数量
    */
    @Override
    public int updateParamsBySelective(User params,User record) {
        return userMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 系统用户 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<User> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<User>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<User> records : list) {
            int count = userMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 系统用户 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<User> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<User>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<User> records : listLists) {
            int count = userMapper.updateBatchSelective(records);
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
    public int deleteBySelective(User record){
        return userMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 系统用户 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 系统用户 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return userMapper.deleteByPrimaryKeys(ids);
    }

}
