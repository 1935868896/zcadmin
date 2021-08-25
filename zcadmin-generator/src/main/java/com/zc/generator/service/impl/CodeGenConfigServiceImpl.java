package com.zc.generator.service.impl;

import java.util.List;

import com.zc.generator.entity.CodeGenConfig;
import com.zc.generator.mapper.CodeGenConfigMapper;
import com.zc.generator.service.CodeGenConfigService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * 代码生成器配置 服务层实现
 *
 * @author Zhang C
 * @date 2021-08-20
 */
@Service
@RequiredArgsConstructor
public class CodeGenConfigServiceImpl extends ServiceImpl<CodeGenConfigMapper, CodeGenConfig> implements CodeGenConfigService {

    private final CodeGenConfigMapper codeGenConfigMapper;

    /**
     * 查询代码生成器配置信息
     *
     * @param id 代码生成器配置ID
     * @return 代码生成器配置信息
     */
    @Override
    public CodeGenConfig selectByPrimaryKey(Long id) {
        return codeGenConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public CodeGenConfig selectOneBySelective(CodeGenConfig record) {
        return codeGenConfigMapper.selectOneBySelective(record);
    }


    /**
     * 根据条件,查询代码生成器配置列表
     *
     * @param record 代码生成器配置信息
     * @return 代码生成器配置集合
     */
    @Override
    public List<CodeGenConfig> selectListBySelective(CodeGenConfig record) {
        return codeGenConfigMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,分页查询代码生成器配置列表
     *
     * @param record 代码生成器配置信息
     * @param page mybatis-plus 分页对象
     * @return 代码生成器配置集合
     */
    @Override
    public IPage<CodeGenConfig> selectPageBySelective(CodeGenConfig record, Page page) {
        return codeGenConfigMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询代码生成器配置列表
     *
     * @param ids 代码生成器配置主键List集合
     * @return 代码生成器配置集合
     */
    @Override
    public List<CodeGenConfig> selectByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return codeGenConfigMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 代码生成器配置 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(CodeGenConfig record) {
        return codeGenConfigMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 代码生成器配置 信息
     * @return 插入数量
     */
    @Override
    public int insert(CodeGenConfig record) {
        return codeGenConfigMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 代码生成器配置 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(CodeGenConfig record) {
        return codeGenConfigMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 代码生成器配置集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<CodeGenConfig> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<CodeGenConfig>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<CodeGenConfig> records : list) {
            int count = codeGenConfigMapper.insertBatch(records);
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
    public int update(CodeGenConfig record) {
        return codeGenConfigMapper.update(record);
    }
    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 代码生成器配置 信息
     * @return 修改数量
     */
    @Override
    public int updateBySelective(CodeGenConfig record) {
        return codeGenConfigMapper.updateBySelective(record);
    }
    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 代码生成器配置 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<CodeGenConfig> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<CodeGenConfig>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<CodeGenConfig> records : list) {
            int count = codeGenConfigMapper.updateBatch(records);
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
    public int updateBatchBySelective(List<CodeGenConfig> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<CodeGenConfig>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<CodeGenConfig> records : listLists) {
            int count = codeGenConfigMapper.updateBatchSelective(records);
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
        return codeGenConfigMapper.deleteByPrimaryKey(id);
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
        return codeGenConfigMapper.deleteByPrimaryKeys(ids);
    }

}
