package com.zc.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.system.mapper.SysUserThirdAuthMapper;
import com.zc.modules.system.entity.SysUserThirdAuth;
import com.zc.modules.system.service.SysUserThirdAuthService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 第三方认证用户 服务层实现
 *
 * @author zhangc
 * @date 2021-10-14
 */
@Service
@RequiredArgsConstructor
public class SysUserThirdAuthServiceImpl extends ServiceImpl<SysUserThirdAuthMapper, SysUserThirdAuth> implements SysUserThirdAuthService {

    private final SysUserThirdAuthMapper sysUserThirdAuthMapper;
    /**
     * 根据条件,查询第一个第三方认证用户对象(一般用于条件可以确定唯一数据)
     *
     * @param record 第三方认证用户信息
     * @return 第三方认证用户
     */
    @Override
    public SysUserThirdAuth selectOneByParam(SysUserThirdAuth record) {
        return sysUserThirdAuthMapper.selectOneByParam(record);
    }
    /**
     * 插入单条数据
     *
     * @param record 第三方认证用户 信息
     * @return 插入数量
     */
    @Override
    public int insertOne(SysUserThirdAuth record) {
        return sysUserThirdAuthMapper.insertOne(record);
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 第三方认证用户 信息
     * @return 修改数量
     */
    @Override
    public boolean updateById(SysUserThirdAuth record) {
        return sysUserThirdAuthMapper.updateById(record)>0;
    }
    /**
     * 根据主键删除数据
     *
     * @param id 第三方认证用户 主键
     * @return 删除数量
     */
    @Override
    public boolean deleteById(Long id) {
        return sysUserThirdAuthMapper.deleteById(id)>0;
    }

}
