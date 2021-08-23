package com.zc.generator.service.impl;

import java.util.List;

import com.zc.generator.entity.GenConfig;
import com.zc.generator.mapper.GenConfigMapper;
import com.zc.generator.service.GenConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 代码生成器配置 服务层实现
 *
 * @author Zhang C
 * @date 2021-08-20
 */
@Service
@RequiredArgsConstructor
public class GenConfigServiceImpl extends ServiceImpl<GenConfigMapper, GenConfig> implements GenConfigService {

    private final GenConfigMapper genConfigMapper;

    /**
     * 查询代码生成器配置信息
     *
     * @param id 代码生成器配置ID
     * @return 代码生成器配置信息
     */
    @Override
    public GenConfig selectByPrimaryKey(Long id) {
        return genConfigMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询代码生成器配置列表
     *
     * @param record 代码生成器配置信息
     * @return 代码生成器配置集合
     */
    @Override
    public List<GenConfig> selectListBySelective(GenConfig record) {
        return genConfigMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,分页查询代码生成器配置列表
     *
     * @param record 代码生成器配置信息
     * @param page mybatis-plus 分页对象
     * @return 代码生成器配置集合
     */
    @Override
    public IPage<GenConfig> selectPageBySelective(GenConfig record, Page page) {
        return genConfigMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询代码生成器配置列表
     *
     * @param ids 代码生成器配置主键List集合
     * @return 代码生成器配置集合
     */
    @Override
    public List<GenConfig> selectByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return genConfigMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 代码生成器配置 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(GenConfig record) {
        return genConfigMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 代码生成器配置 信息
     * @return 插入数量
     */
    @Override
    public int insert(GenConfig record) {
        return genConfigMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 代码生成器配置 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(GenConfig record) {
        return genConfigMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 代码生成器配置集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<GenConfig> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<GenConfig>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<GenConfig> records : list) {
            int count = genConfigMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 代码生成器配置 信息
     * @return 修改数量
     */
    @Override
    public int update(GenConfig record) {
        return genConfigMapper.update(record);
    }
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 代码生成器配置 信息
     * @return 修改数量
     */
    @Override
    public int updateBySelective(GenConfig record) {
        return genConfigMapper.updateBySelective(record);
    }
    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 代码生成器配置 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<GenConfig> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<GenConfig>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<GenConfig> records : list) {
            int count = genConfigMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 代码生成器配置 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<GenConfig> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<GenConfig>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<GenConfig> records : listLists) {
            int count = genConfigMapper.updateBatchSelective(records);
            result = result + count;
        }
        return result;
    }
    /**
     * 根据主键删除数据
     *
     * @param id 代码生成器配置 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return genConfigMapper.deleteByPrimaryKey(id);
    }
    /**
     * 根据主键集合删除数据
     *
     * @param ids 代码生成器配置 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return genConfigMapper.deleteByPrimaryKeys(ids);
    }

}
