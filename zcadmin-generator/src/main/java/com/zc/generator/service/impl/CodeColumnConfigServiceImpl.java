package com.zc.generator.service.impl;

import java.util.List;

import com.zc.generator.entity.CodeColumnConfig;
import com.zc.generator.mapper.CodeColumnConfigMapper;
import com.zc.generator.service.CodeColumnConfigService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * 代码生成字段存储 服务层实现
 *
 * @author Zhang C
 * @date 2021-08-20
 */
@Service
@RequiredArgsConstructor
public class CodeColumnConfigServiceImpl extends ServiceImpl<CodeColumnConfigMapper, CodeColumnConfig> implements CodeColumnConfigService {

    private final CodeColumnConfigMapper columnConfigMapper;

    /**
     * 查询代码生成字段存储信息
     *
     * @param id 代码生成字段存储ID
     * @return 代码生成字段存储信息
     */
    @Override
    public CodeColumnConfig selectByPrimaryKey(Long id) {
        return columnConfigMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询代码生成字段存储列表
     *
     * @param record 代码生成字段存储信息
     * @return 代码生成字段存储集合
     */
    @Override
    public List<CodeColumnConfig> selectListBySelective(CodeColumnConfig record) {
        return columnConfigMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,分页查询代码生成字段存储列表
     *
     * @param record 代码生成字段存储信息
     * @param page mybatis-plus 分页对象
     * @return 代码生成字段存储集合
     */
    @Override
    public IPage<CodeColumnConfig> selectPageBySelective(CodeColumnConfig record, Page page) {
        return columnConfigMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询代码生成字段存储列表
     *
     * @param ids 代码生成字段存储主键List集合
     * @return 代码生成字段存储集合
     */
    @Override
    public List<CodeColumnConfig> selectByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return columnConfigMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 代码生成字段存储 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(CodeColumnConfig record) {
        return columnConfigMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 代码生成字段存储 信息
     * @return 插入数量
     */
    @Override
    public int insert(CodeColumnConfig record) {
        return columnConfigMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 代码生成字段存储 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(CodeColumnConfig record) {
        return columnConfigMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 代码生成字段存储集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<CodeColumnConfig> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<CodeColumnConfig>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<CodeColumnConfig> records : list) {
            int count = columnConfigMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 代码生成字段存储 信息
     * @return 修改数量
     */
    @Override
    public int update(CodeColumnConfig record) {
        return columnConfigMapper.update(record);
    }
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 代码生成字段存储 信息
     * @return 修改数量
     */
    @Override
    public int updateBySelective(CodeColumnConfig record) {
        return columnConfigMapper.updateBySelective(record);
    }
    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 代码生成字段存储 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<CodeColumnConfig> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<CodeColumnConfig>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<CodeColumnConfig> records : list) {
            int count = columnConfigMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 代码生成字段存储 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<CodeColumnConfig> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<CodeColumnConfig>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<CodeColumnConfig> records : listLists) {
            int count = columnConfigMapper.updateBatchSelective(records);
            result = result + count;
        }
        return result;
    }
    /**
     * 根据主键删除数据
     *
     * @param id 代码生成字段存储 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return columnConfigMapper.deleteByPrimaryKey(id);
    }
    /**
     * 根据主键集合删除数据
     *
     * @param ids 代码生成字段存储 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return columnConfigMapper.deleteByPrimaryKeys(ids);
    }

}
