package com.zc.modules.system.service.impl;

import com.zc.modules.system.entity.SysUser;
import com.zc.modules.system.mapper.SysUserMapper;
import com.zc.modules.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author ZhangC
 * @since 2021-08-02
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
