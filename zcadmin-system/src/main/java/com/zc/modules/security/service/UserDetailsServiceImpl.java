package com.zc.modules.security.service;

/**
 * @author ZhangC
 * @create 2021-08-02-17:11
 */

import com.zc.modules.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //此处为从数据库获取UserDetails
        UserDetails userDetails = userMapper.selectUserByUserName(s);

        //查permission
        Set<String> permissionSet = userMapper.selectPermissionByUsername(s);
        Set<String> roleSet = userMapper.selectRolesByUsername(s);
        permissionSet.addAll(roleSet);
        ((UserDto) userDetails).setPermission(permissionSet);

        return userDetails;
    }
}
