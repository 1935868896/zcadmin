package com.zc.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.system.mapper.SysDictMapper;
import com.zc.modules.system.entity.SysDict;
import com.zc.modules.system.service.SysDictService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据字典 服务层实现
 *
 * @author zhangc
 * @date 2021-09-30
 */
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private final SysDictMapper sysDictMapper;
    /**
     * 查询数据字典信息
     *
     * @param id 数据字典ID
     * @return 数据字典信息
     */
    @Override
    public SysDict selectObjectById(Long id) {
        return sysDictMapper.selectObjectById(id);
    }

    /**
     * 根据条件,查询第一个数据字典对象(一般用于条件可以确定唯一数据)
     *
     * @param record 数据字典信息
     * @return 数据字典
     */
    @Override
    public SysDict selectOneByParam(SysDict record) {
        return sysDictMapper.selectOneByParam(record);
    }
    /**
     * 根据条件,分页查询数据字典列表
     *
     * @param record 数据字典信息
     * @param page mybatis-plus 分页对象
     * @return 数据字典集合
     */
    @Override
    public IPage<SysDict> selectPageByParam(SysDict record, Page page) {
        return sysDictMapper.selectPageByParam(record, page);
    }
    /**
     * 插入单条数据
     *
     * @param record 数据字典 信息
     * @return 插入数量
     */
    @Override
    public int insertOne(SysDict record) {
        return sysDictMapper.insertOne(record);
    }


    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 数据字典 信息
     * @return 修改数量
     */
    @Override
    public boolean updateById(SysDict record) {
        return sysDictMapper.updateById(record)>0;
    }
    /**
     * 根据主键删除数据
     *
     * @param id 数据字典 主键
     * @return 删除数量
     */
    @Override
    public int deleteById(Long id) {
        return sysDictMapper.deleteById(id);
    }

}
