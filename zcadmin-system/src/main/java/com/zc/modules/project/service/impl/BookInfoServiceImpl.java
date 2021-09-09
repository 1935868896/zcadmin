package com.zc.modules.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.project.mapper.BookInfoMapper;
import com.zc.modules.project.entity.BookInfo;
import com.zc.modules.project.service.BookInfoService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 图书 服务层实现
 *
 * @author zhangc
 * @date 2021-09-09
 */
@Service
@RequiredArgsConstructor
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements BookInfoService {

    private final BookInfoMapper bookInfoMapper;

    /**
     * 查询图书信息
     *
     * @param id 图书ID
     * @return 图书信息
     */
    @Override
    public BookInfo selectByPrimaryKey(Integer id) {
        return bookInfoMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询图书列表
     *
     * @param record 图书信息
     * @return 图书集合
     */
    @Override
    public List<BookInfo> selectListBySelective(BookInfo record) {
        return bookInfoMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个图书对象(一般用于条件可以确定唯一数据)
     *
     * @param record 图书信息
     * @return 图书
     */
    @Override
    public BookInfo selectOneBySelective(BookInfo record) {
        return bookInfoMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询图书列表
     *
     * @param record 图书信息
     * @param page mybatis-plus 分页对象
     * @return 图书集合
     */
    @Override
    public IPage<BookInfo> selectPageBySelective(BookInfo record, Page page) {
        return bookInfoMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询图书列表
     *
     * @param ids 图书主键List集合
     * @return 图书集合
     */
    @Override
    public List<BookInfo> selectByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return bookInfoMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 图书 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(BookInfo record) {
        return bookInfoMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 图书 信息
     * @return 插入数量
     */
    @Override
    public int insert(BookInfo record) {
        return bookInfoMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 图书 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(BookInfo record) {
        return bookInfoMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 图书集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<BookInfo> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<BookInfo>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<BookInfo> records : list) {
            int count = bookInfoMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 图书 信息
     * @return 修改数量
     */
    @Override
    public int update(BookInfo record) {
        return bookInfoMapper.update(record);
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 图书 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(BookInfo record) {
        return bookInfoMapper.updateSelective(record);
    }


    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
    *
    * @param  params  需要修改的信息
    * @param  record  根据的条件信息
    * @return 修改数量
    */
    @Override
    public int updateParamsBySelective(BookInfo params,BookInfo record) {
        return bookInfoMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 图书 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<BookInfo> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<BookInfo>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<BookInfo> records : list) {
            int count = bookInfoMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 图书 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<BookInfo> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<BookInfo>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<BookInfo> records : listLists) {
            int count = bookInfoMapper.updateBatchSelective(records);
            result = result + count;
        }
        return result;
    }

    /**
    * 根据条件删除数据
    *
    * @param record  删除的条件
    * @return 删除数量
    */
    public int deleteBySelective(BookInfo record){
        return bookInfoMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 图书 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return bookInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 图书 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return bookInfoMapper.deleteByPrimaryKeys(ids);
    }

}
