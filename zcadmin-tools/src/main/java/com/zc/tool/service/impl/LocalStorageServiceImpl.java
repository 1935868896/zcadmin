package com.zc.tool.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.zc.tool.entity.LocalStorage;
import com.zc.tool.mapper.LocalStorageMapper;
import com.zc.tool.service.LocalStorageService;
import com.zc.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * 本地存储 服务层实现
 *
 * @author zhangc
 * @date 2021-09-03
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LocalStorageServiceImpl extends ServiceImpl<LocalStorageMapper, LocalStorage> implements LocalStorageService {

    private final LocalStorageMapper localStorageMapper;

    /**
     * 查询本地存储信息
     *
     * @param id 本地存储ID
     * @return 本地存储信息
     */
    @Override
    public LocalStorage selectByPrimaryKey(Long id) {
        return localStorageMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询本地存储列表
     *
     * @param record 本地存储信息
     * @return 本地存储集合
     */
    @Override
    public List<LocalStorage> selectListBySelective(LocalStorage record) {
        return localStorageMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个本地存储对象(一般用于条件可以确定唯一数据)
     *
     * @param record 本地存储信息
     * @return 本地存储
     */
    @Override
    public LocalStorage selectOneBySelective(LocalStorage record) {
        return localStorageMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询本地存储列表
     *
     * @param record 本地存储信息
     * @param page mybatis-plus 分页对象
     * @return 本地存储集合
     */
    @Override
    public IPage<LocalStorage> selectPageBySelective(LocalStorage record, Page page) {
        return localStorageMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询本地存储列表
     *
     * @param ids 本地存储主键List集合
     * @return 本地存储集合
     */
    @Override
    public List<LocalStorage> selectByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return localStorageMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 本地存储 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(LocalStorage record) {
        return localStorageMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 本地存储 信息
     * @return 插入数量
     */
    @Override
    public int insert(LocalStorage record) {
        record.setCreateBy(SecurityUtils.getCurrentUsername());
        record.setCreateTime(new Date());
        return localStorageMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 本地存储 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(LocalStorage record) {
        return localStorageMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 本地存储集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<LocalStorage> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<LocalStorage>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<LocalStorage> records : list) {
            int count = localStorageMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 本地存储 信息
     * @return 修改数量
     */
    @Override
    public int update(LocalStorage record) {
        //需要查询不包含自己
        record.setUpdateBy(SecurityUtils.getCurrentUsername());
        record.setUpdateTime(new Date());
        return localStorageMapper.update(record);
    }

    @Override
    public int selectCountName(LocalStorage record) {
        return localStorageMapper.selectCountName(record.getStorageId(),record.getName());
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 本地存储 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(LocalStorage record) {
        return localStorageMapper.updateSelective(record);
    }


    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
    *
    * @param  params  需要修改的信息
    * @param  record  根据的条件信息
    * @return 修改数量
    */
    @Override
    public int updateParamsBySelective(LocalStorage params,LocalStorage record) {
        return localStorageMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 本地存储 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<LocalStorage> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<LocalStorage>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<LocalStorage> records : list) {
            int count = localStorageMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 本地存储 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<LocalStorage> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<LocalStorage>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<LocalStorage> records : listLists) {
            int count = localStorageMapper.updateBatchSelective(records);
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
    public int deleteBySelective(LocalStorage record){
        return localStorageMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 本地存储 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        LocalStorage localStorage = localStorageMapper.selectByPrimaryKey(id);
        File file = new File(localStorage.getPath());
        log.info("删除本地文件:{}",localStorage.getPath());
        if (file.exists()){//文件是否存在
            file.delete();//删除文件
        }
        return localStorageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 本地存储 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return localStorageMapper.deleteByPrimaryKeys(ids);
    }

}
