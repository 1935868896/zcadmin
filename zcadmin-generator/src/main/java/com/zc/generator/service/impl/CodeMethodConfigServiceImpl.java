package com.zc.generator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.generator.mapper.CodeMethodConfigMapper;
import com.zc.generator.entity.CodeMethodConfig;
import com.zc.generator.service.CodeMethodConfigService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 代码生成方法 服务层实现
 *
 * @author zhangc
 * @date 2021-09-23
 */
@Service
@RequiredArgsConstructor
public class CodeMethodConfigServiceImpl extends ServiceImpl<CodeMethodConfigMapper, CodeMethodConfig> implements CodeMethodConfigService {

    private final CodeMethodConfigMapper codeMethodConfigMapper;

    /**
     * 查询代码生成方法信息
     *
     * @param id 代码生成方法ID
     * @return 代码生成方法信息
     */
    @Override
    public CodeMethodConfig selectByPrimaryKey(Integer id) {
        return codeMethodConfigMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询代码生成方法列表
     *
     * @param record 代码生成方法信息
     * @return 代码生成方法集合
     */
    @Override
    public List<CodeMethodConfig> selectListBySelective(CodeMethodConfig record) {
        return codeMethodConfigMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个代码生成方法对象(一般用于条件可以确定唯一数据)
     *
     * @param record 代码生成方法信息
     * @return 代码生成方法
     */
    @Override
    public CodeMethodConfig selectOneBySelective(CodeMethodConfig record) {
        return codeMethodConfigMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询代码生成方法列表
     *
     * @param record 代码生成方法信息
     * @param page mybatis-plus 分页对象
     * @return 代码生成方法集合
     */
    @Override
    public IPage<CodeMethodConfig> selectPageBySelective(CodeMethodConfig record, Page page) {
        return codeMethodConfigMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询代码生成方法列表
     *
     * @param ids 代码生成方法主键List集合
     * @return 代码生成方法集合
     */
    @Override
    public List<CodeMethodConfig> selectByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return codeMethodConfigMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 代码生成方法 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(CodeMethodConfig record) {
        return codeMethodConfigMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 代码生成方法 信息
     * @return 插入数量
     */
    @Override
    public int insert(CodeMethodConfig record) {
        return codeMethodConfigMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 代码生成方法 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(CodeMethodConfig record) {
        return codeMethodConfigMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 代码生成方法集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<CodeMethodConfig> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<CodeMethodConfig>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<CodeMethodConfig> records : list) {
            int count = codeMethodConfigMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 代码生成方法 信息
     * @return 修改数量
     */
    @Override
    public int update(CodeMethodConfig record) {
        return codeMethodConfigMapper.update(record);
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 代码生成方法 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(CodeMethodConfig record) {
        return codeMethodConfigMapper.updateSelective(record);
    }


    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
    *
    * @param  params  需要修改的信息
    * @param  record  根据的条件信息
    * @return 修改数量
    */
    @Override
    public int updateParamsBySelective(CodeMethodConfig params,CodeMethodConfig record) {
        return codeMethodConfigMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 代码生成方法 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<CodeMethodConfig> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<CodeMethodConfig>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<CodeMethodConfig> records : list) {
            int count = codeMethodConfigMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 代码生成方法 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<CodeMethodConfig> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<CodeMethodConfig>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<CodeMethodConfig> records : listLists) {
            int count = codeMethodConfigMapper.updateBatchSelective(records);
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
    public int deleteBySelective(CodeMethodConfig record){
        return codeMethodConfigMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 代码生成方法 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return codeMethodConfigMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 代码生成方法 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return codeMethodConfigMapper.deleteByPrimaryKeys(ids);
    }

}
