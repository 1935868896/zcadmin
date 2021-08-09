package com.zc.modules.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.project.mapper.BookinfoMapper;
import com.zc.modules.project.entity.Bookinfo;
import com.zc.modules.project.service.BookinfoService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 图书 服务层实现
 *
 * @author ruoyi
 * @date 2021-08-09
 */
@Service
@RequiredArgsConstructor
public class BookinfoServiceImpl extends ServiceImpl<BookinfoMapper, Bookinfo> implements BookinfoService {

    private final BookinfoMapper bookinfoMapper;

    /**
     * 查询图书信息
     *
     * @param id 图书ID
     * @return 图书信息
     */
    @Override
    public Bookinfo selectByPrimaryKey(Integer id) {
        return bookinfoMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询图书列表
     *
     * @param record 图书信息
     * @return 图书集合
     */
    @Override
    public List<Bookinfo> selectListBySelective(Bookinfo record) {
        return bookinfoMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,分页查询图书列表
     *
     * @param record 图书信息
     * @param page mybatis-plus 分页对象
     * @return 图书集合
     */
    @Override
    public IPage<Bookinfo> selectPageBySelective(Bookinfo record, Page page) {
        return bookinfoMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询图书列表
     *
     * @param ids 图书主键List集合
     * @return 图书集合
     */
    @Override
    public List<Bookinfo> selectByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return bookinfoMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 图书 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(Bookinfo record) {
        return bookinfoMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 图书 信息
     * @return 插入数量
     */
    @Override
    public int insert(Bookinfo record) {
        return bookinfoMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 图书 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(Bookinfo record) {
        return bookinfoMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 图书集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<Bookinfo> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<Bookinfo>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Bookinfo> records : list) {
            int count = bookinfoMapper.insertBatch(records);
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
    public int updateByPrimaryKey(Bookinfo record) {
        return bookinfoMapper.updateByPrimaryKey(record);
    }
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 图书 信息
     * @return 修改数量
     */
    @Override
    public int updateByPrimaryKeySelective(Bookinfo record) {
        return bookinfoMapper.updateByPrimaryKeySelective(record);
    }
    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 图书 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<Bookinfo> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<Bookinfo>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Bookinfo> records : list) {
            int count = bookinfoMapper.updateBatch(records);
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
    public int updateBatchSelective(List<Bookinfo> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<Bookinfo>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Bookinfo> records : listLists) {
            int count = bookinfoMapper.updateBatchSelective(records);
            result = result + count;
        }
        return result;
    }
    /**
     * 根据主键删除数据
     *
     * @param id 图书 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return bookinfoMapper.deleteByPrimaryKey(id);
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
        return bookinfoMapper.deleteByPrimaryKeys(ids);
    }

}
