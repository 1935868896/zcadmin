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

    List<Book> selectListBySelective(Book book);

    IPage<Book> selectPageBySelective(Book book, Page page);

    int selectCountBySelective(Book book);

    List<Book> selectByPrimaryKeys(List<Integer> ids);

    int deleteByPrimaryKeys(List<Integer> id);

    int insertBatch(List<Book> recordList);

    int updateBatch(List<Book> bookList);

    int updateBatchSelective(List<Book> bookList);
}
