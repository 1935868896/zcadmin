package com.zc.modules.system.service;

import com.zc.modules.system.entity.SysUserThirdAuth;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 第三方认证用户 服务层
 *
 * @author zhangc
 * @date 2021-10-14
 */
public interface SysUserThirdAuthService extends IService<SysUserThirdAuth> {
    /**
     * 根据条件,查询第一个第三方认证用户对象(一般用于条件可以确定唯一数据)
     *
     * @param record 第三方认证用户信息
     * @return 第三方认证用户
     */
        SysUserThirdAuth selectOneByParam(SysUserThirdAuth record);
    /**
     * 插入单条数据
     *
     * @param record 第三方认证用户 信息
     * @return 插入数量
     */
    int insertOne(SysUserThirdAuth record);


    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 第三方认证用户 信息
     * @return 修改数量
     */
    boolean updateById(SysUserThirdAuth record);

    /**
     * 根据主键删除数据
     *
     * @param id 第三方认证用户 主键
     * @return 删除数量
     */
    boolean deleteById(Long id);
}
