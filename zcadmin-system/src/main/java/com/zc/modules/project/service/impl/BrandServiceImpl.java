package com.zc.modules.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.project.mapper.BrandMapper;
import com.zc.modules.project.entity.Brand;
import com.zc.modules.project.service.BrandService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 品牌 服务层实现
 *
 * @author zhangc
 * @date 2021-08-25
 */
@Service
@RequiredArgsConstructor
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    private final BrandMapper brandMapper;

    /**
     * 查询品牌信息
     *
     * @param id 品牌ID
     * @return 品牌信息
     */
    @Override
    public Brand selectByPrimaryKey(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询品牌列表
     *
     * @param record 品牌信息
     * @return 品牌集合
     */
    @Override
    public List<Brand> selectListBySelective(Brand record) {
        return brandMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个品牌对象(一般用于条件可以确定唯一数据)
     *
     * @param record 品牌信息
     * @return 品牌
     */
    @Override
    public Brand selectOneBySelective(Brand record) {
        return brandMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询品牌列表
     *
     * @param record 品牌信息
     * @param page mybatis-plus 分页对象
     * @return 品牌集合
     */
    @Override
    public IPage<Brand> selectPageBySelective(Brand record, Page page) {
        return brandMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询品牌列表
     *
     * @param ids 品牌主键List集合
     * @return 品牌集合
     */
    @Override
    public List<Brand> selectByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return brandMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 品牌 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(Brand record) {
        return brandMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 品牌 信息
     * @return 插入数量
     */
    @Override
    public int insert(Brand record) {
        return brandMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 品牌 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(Brand record) {
        return brandMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 品牌集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<Brand> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<Brand>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Brand> records : list) {
            int count = brandMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 品牌 信息
     * @return 修改数量
     */
    @Override
    public int update(Brand record) {
        return brandMapper.update(record);
    }
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 品牌 信息
     * @return 修改数量
     */
    @Override
    public int updateBySelective(Brand record) {
        return brandMapper.updateBySelective(record);
    }
    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 品牌 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<Brand> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<Brand>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Brand> records : list) {
            int count = brandMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 品牌 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<Brand> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<Brand>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Brand> records : listLists) {
            int count = brandMapper.updateBatchSelective(records);
            result = result + count;
        }
        return result;
    }
    /**
     * 根据主键删除数据
     *
     * @param id 品牌 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }
    /**
     * 根据主键集合删除数据
     *
     * @param ids 品牌 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return brandMapper.deleteByPrimaryKeys(ids);
    }

}
