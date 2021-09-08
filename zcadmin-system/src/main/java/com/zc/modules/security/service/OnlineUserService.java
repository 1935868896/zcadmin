package com.zc.modules.security.service;

import com.alibaba.fastjson.JSON;
import com.zc.entity.JwtAuthentication;
import com.zc.modules.security.entity.OnlineUserDto;
import com.zc.utils.PageUtil;
import com.zc.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangC
 * @create 2021-09-08-16:37
 */
@Component
public class OnlineUserService {

    public Map<String,Object> getAll( Pageable pageable){
        List<OnlineUserDto> onlineUserDtos = getAll();
        return PageUtil.toPage(
                PageUtil.toPage(pageable.getPageNumber(),pageable.getPageSize(), onlineUserDtos),
                onlineUserDtos.size()
        );
    }
    List<OnlineUserDto> getAll(){
        Set<String> keys = RedisUtil.KeyOps.keys("online:user:" + "*");
        List<OnlineUserDto> list=new ArrayList<>();
        for (String key : keys) {
            String redisString = RedisUtil.StringOps.get(key);
            if (StringUtils.isNotBlank(redisString)){
                OnlineUserDto parse = JSON.parseObject(redisString, OnlineUserDto.class);
                list.add(parse);
            }
        }
        return list;
    }


    /**
     * 踢出用户
     * @param key /
     */
    public void kickOut(String key){
        //删除用户,且将jwt加入黑名单
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        RedisUtil.KeyOps.delete("online:user:"+key);
        RedisUtil.StringOps.setEx("jwt-black:list:" + key,format , 8, TimeUnit.DAYS);
    }
  //
    public void logOut(String key){
        //删除在线用户,且将jwt加入黑名单
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        RedisUtil.KeyOps.delete("online:user:"+key);
        RedisUtil.StringOps.setEx("jwt-black:list:" + key,format , 8, TimeUnit.DAYS);

    }


}
