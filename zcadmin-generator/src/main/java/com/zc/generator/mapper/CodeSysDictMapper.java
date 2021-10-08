package com.zc.generator.mapper;

import com.zc.generator.entity.CodeSysDictDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-10-08-15:43
 */
@Mapper
public interface CodeSysDictMapper {
    @Select("SELECT *  FROM `sys_dict_detail`  WHERE\n" +
            "\tdict_id = ( SELECT dict_id FROM sys_dict WHERE NAME = #{name} )")
    List<CodeSysDictDetail> selectDictDetail(String name);
}
