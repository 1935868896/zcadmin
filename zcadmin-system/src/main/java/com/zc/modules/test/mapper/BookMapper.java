package com.zc.modules.test.mapper;

import com.zc.modules.test.entity.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}