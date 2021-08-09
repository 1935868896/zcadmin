package com.zc.modules.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.modules.test.entity.*;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;


/**
 *  BookMapper 由 mybatis generator反向生成插件生成
 *  这里列举还需要添加的几种代码:
 *  1.条件查询
 *  2.分页查询
 *  3.查询数量
 *  4.根据id批量查询 List<Long> ids
 *  5.批量插入
 *  6.批量修改
 *  7.根据id批量删除 List<Long> ids
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    /**
     *
     * @param book
     * @return
     */
    List<Book> selectListBySelective(Book book);
    //分页查询

    /**
     *
     * @param record
     * @param page
     * @return
     */
    IPage<Book> selectPageBySelective(Book record, Page page);
    //查询数量
    int selectCountBySelective(Book book);
    //根据id批量查询
    List<Book> selectByPrimaryKeys(List<Integer> ids);
    //根据id批量查询
    int deleteByPrimaryKeys(List<Integer> id);
    //批量插入
    int insertBatch(List<Book> recordList);

    /**
     * 批量修改分为两种 :
     *  1.修改整条数据
     *  2.只修改传过来不为null的数据
     */
    //批量修改
    int updateBatch(List<Book> bookList);

    int updateBatchSelective(List<Book> bookList);






}