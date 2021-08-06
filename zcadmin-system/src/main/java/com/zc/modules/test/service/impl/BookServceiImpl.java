package com.zc.modules.test.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.test.entity.Book;
import com.zc.modules.test.mapper.BookMapper;
import com.zc.modules.test.service.BookService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-08-04-10:53
 */
@Service
@RequiredArgsConstructor
public class BookServceiImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    private  final BookMapper bookMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return bookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Book record) {
        return bookMapper.insert(record);
    }

    @Override
    public int insertSelective(Book record) {
        return bookMapper.insertSelective(record);
    }

    @Override
    public Book selectByPrimaryKey(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Book record) {
        return bookMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Book record) {
        return bookMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Book> selectBySelective(Book book) {
        return bookMapper.selectBySelective(book);
    }

    @Override
    public IPage<Book> selectPageBySelective(Book book, Page page) {
        return bookMapper.selectPageBySelective(book,page);
    }

    @Override
    public Long selectCountBySelective(Book book) {
        return bookMapper.selectCountBySelective(book);
    }

    @Override
    public List<Book> selectByPrimaryKeys(List<Integer> ids) {
        if (ids==null||ids.size()<=0){
            return null;
        }
        return bookMapper.selectByPrimaryKeys(ids);
    }

    @Override
    public Long deleteByPrimaryKeys(List<Integer> ids) {
        if (ids==null||ids.size()<=0){
            return null;
        }
        return bookMapper.deleteByPrimaryKeys(ids);
    }

    @Override
    @Transactional
    public Long insertBatch(List<Book> recordList) {
        Long result=0l;
        if (recordList==null||recordList.size()<=0){
            return result;
        }
        List<List<Book>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Book> books : list) {
            Long count = bookMapper.insertBatch(books);
            result=result+count;
        }
        return result;
    }

    @Override
    @Transactional
    public Long updateBatch(List<Book> recordList) {
        Long result=0l;
        if (recordList==null||recordList.size()<=0){
            return result;
        }
        List<List<Book>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Book> books : list) {
            Long count = bookMapper.updateBatch(books);
            result=result+count;
        }
        return result;
    }

    @Override
    @Transactional
    public Long updateBatchSelective(List<Book> recordList) {
        Long result=0l;
        if (recordList==null||recordList.size()<=0){
            return result;
        }
        List<List<Book>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Book> books : listLists) {
            Long count = bookMapper.updateBatchSelective(books);
            result=result+count;
        }
        return result;
    }
}
