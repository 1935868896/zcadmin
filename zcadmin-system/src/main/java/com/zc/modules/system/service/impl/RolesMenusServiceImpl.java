package com.zc.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.system.mapper.RolesMenusMapper;
import com.zc.modules.system.entity.RolesMenus;
import com.zc.modules.system.service.RolesMenusService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色菜单关联 服务层实现
 *
 * @author zhangc
 * @date 2021-09-01
 */
@Service
@RequiredArgsConstructor
public class RolesMenusServiceImpl extends ServiceImpl<RolesMenusMapper, RolesMenus> implements RolesMenusService {

    private final RolesMenusMapper rolesMenusMapper;

    /**
     * 查询角色菜单关联信息
     *
     * @param id 角色菜单关联ID
     * @return 角色菜单关联信息
     */
    @Override
    public RolesMenus selectByPrimaryKey(Long id) {
        return rolesMenusMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询角色菜单关联列表
     *
     * @param record 角色菜单关联信息
     * @return 角色菜单关联集合
     */
    @Override
    public List<RolesMenus> selectListBySelective(RolesMenus record) {
        return rolesMenusMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个角色菜单关联对象(一般用于条件可以确定唯一数据)
     *
     * @param record 角色菜单关联信息
     * @return 角色菜单关联
     */
    @Override
    public RolesMenus selectOneBySelective(RolesMenus record) {
        return rolesMenusMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询角色菜单关联列表
     *
     * @param record 角色菜单关联信息
     * @param page mybatis-plus 分页对象
     * @return 角色菜单关联集合
     */
    @Override
    public IPage<RolesMenus> selectPageBySelective(RolesMenus record, Page page) {
        return rolesMenusMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询角色菜单关联列表
     *
     * @param ids 角色菜单关联主键List集合
     * @return 角色菜单关联集合
     */
    @Override
    public List<RolesMenus> selectByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return rolesMenusMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 角色菜单关联 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(RolesMenus record) {
        return rolesMenusMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 角色菜单关联 信息
     * @return 插入数量
     */
    @Override
    public int insert(RolesMenus record) {
        return rolesMenusMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 角色菜单关联 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(RolesMenus record) {
        return rolesMenusMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 角色菜单关联集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<RolesMenus> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<RolesMenus>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<RolesMenus> records : list) {
            int count = rolesMenusMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 角色菜单关联 信息
     * @return 修改数量
     */
    @Override
    public int update(RolesMenus record) {
        return rolesMenusMapper.update(record);
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 角色菜单关联 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(RolesMenus record) {
        return rolesMenusMapper.updateSelective(record);
    }


    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
    *
    * @param  params  需要修改的信息
    * @param  record  根据的条件信息
    * @return 修改数量
    */
    @Override
    public int updateParamsBySelective(RolesMenus params,RolesMenus record) {
        return rolesMenusMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 角色菜单关联 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<RolesMenus> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<RolesMenus>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<RolesMenus> records : list) {
            int count = rolesMenusMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 角色菜单关联 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<RolesMenus> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<RolesMenus>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<RolesMenus> records : listLists) {
            int count = rolesMenusMapper.updateBatchSelective(records);
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
    public int deleteBySelective(RolesMenus record){
        return rolesMenusMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 角色菜单关联 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return rolesMenusMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 角色菜单关联 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return rolesMenusMapper.deleteByPrimaryKeys(ids);
    }

}
