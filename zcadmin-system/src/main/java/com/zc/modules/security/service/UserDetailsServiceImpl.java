package com.zc.modules.security.service;

/**
 * @author ZhangC
 * @create 2021-08-02-17:11
 */
import com.zc.modules.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    SysUserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //此处为从数据库获取UserDetails
        UserDetails userDetails = userMapper.selectUserByUserName(s);

        //查permission
        ((UserDto) userDetails).setPermission(userMapper.selectPermissionByUsername(s));
        return userDetails;
    }
}