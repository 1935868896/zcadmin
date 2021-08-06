package com.zc.modules.test.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.modules.test.entity.Book;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-08-04-10:51
 */
public interface BookService extends IService<Book> {

    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> selectBySelective(Book book);

    IPage<Book> selectPageBySelective(Book book, Page page);

    Long selectCountBySelective(Book book);

    List<Book> selectByPrimaryKeys(List<Integer> ids);

    Long deleteByPrimaryKeys(List<Integer> id);

    Long insertBatch(List<Book> recordList);

    Long updateBatch(List<Book> bookList);

    Long updateBatchSelective(List<Book> bookList);
}
