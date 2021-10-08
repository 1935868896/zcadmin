package com.zc.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.system.mapper.SysDictDetailMapper;
import com.zc.modules.system.entity.SysDictDetail;
import com.zc.modules.system.service.SysDictDetailService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据字典详情 服务层实现
 *
 * @author zhangc
 * @date 2021-09-30
 */
@Service
@RequiredArgsConstructor
public class SysDictDetailServiceImpl extends ServiceImpl<SysDictDetailMapper, SysDictDetail> implements SysDictDetailService {

    private final SysDictDetailMapper sysDictDetailMapper;
    /**
     * 根据条件,查询数据字典详情列表
     *
     * @param record 数据字典详情信息
     * @return 数据字典详情集合
     */
    @Override
    public List<SysDictDetail> selectListByParam(SysDictDetail record) {
        return sysDictDetailMapper.selectListByParam(record);
    }
    /**
     * 根据条件,分页查询数据字典详情列表
     *
     * @param record 数据字典详情信息
     * @param page mybatis-plus 分页对象
     * @return 数据字典详情集合
     */
    @Override
    public IPage<SysDictDetail> selectPageByParam(SysDictDetail record, Page page) {
        return sysDictDetailMapper.selectPageByParam(record, page);
    }
    /**
     * 插入单条数据
     *
     * @param record 数据字典详情 信息
     * @return 插入数量
     */
    @Override
    public int insertOne(SysDictDetail record) {
        return sysDictDetailMapper.insertOne(record);
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 数据字典详情 信息
     * @return 修改数量
     */
    @Override
    public boolean updateById(SysDictDetail record) {
        return sysDictDetailMapper.updateById(record)>0;
    }
    /**
     * 根据主键删除数据
     *
     * @param id 数据字典详情 主键
     * @return 删除数量
     */
    @Override
    public boolean deleteById(Long id) {
        return sysDictDetailMapper.deleteById(id)>0;
    }

}
