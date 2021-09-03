package com.zc.modules.security.service;

/**
 * @author ZhangC
 * @create 2021-08-02-17:11
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zc.modules.system.mapper.UserMapper;
import com.zc.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //此处为从数据库获取UserDetails

        String redisUserString = (String) RedisUtil.HashOps.hGet("userDetails", username);
        if (redisUserString!=null){
            UserDto parse =JSON.parseObject(redisUserString, UserDto.class);
            return parse;
        }
        UserDetails userDetails = userMapper.selectUserByUserName(username);
        //查permission
        Set<String> permissionSet = userMapper.selectPermissionByUsername(username);
        Set<String> roleSet = userMapper.selectRolesByUsername(username);
        permissionSet.addAll(roleSet);
        ((UserDto) userDetails).setPermission(permissionSet);
        RedisUtil.HashOps.hPutIfAbsent("userDetails",username, JSONObject.toJSONString(userDetails));

        return userDetails;
    }
}
