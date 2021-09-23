package com.zc.generator.mapper;

import com.zc.generator.entity.CodeMethodConfig;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 代码生成方法 数据层
 *
 * @author zhangc
 * @date 2021-09-23
 */
@Mapper
public interface CodeMethodConfigMapper extends BaseMapper<CodeMethodConfig> {

    /**
     * 查询代码生成方法信息
     *
     * @param id 代码生成方法ID
     * @return 代码生成方法信息
     */
    CodeMethodConfig selectByPrimaryKey(Integer id);


    /**
     * 根据条件,查询代码生成方法列表
     *
     * @param record 代码生成方法信息
     * @return 代码生成方法集合
     */
    List<CodeMethodConfig> selectListBySelective(CodeMethodConfig record);

    /**
     * 根据条件,查询第一个代码生成方法对象(一般用于条件可以确定唯一数据)
     *
     * @param record 代码生成方法信息
     * @return 代码生成方法
     */
     CodeMethodConfig selectOneBySelective(CodeMethodConfig record);

    /**
     * 根据条件,分页查询代码生成方法列表
     *
     * @param record 代码生成方法信息
     * @param page mybatis-plus 分页对象
     * @return 代码生成方法集合
     */
    IPage<CodeMethodConfig> selectPageBySelective(CodeMethodConfig record, Page page);
    /**
     * 根据主键集合,批量查询代码生成方法列表
     *
     * @param ids 代码生成方法主键List集合
     * @return 代码生成方法集合
     */
    List<CodeMethodConfig> selectByPrimaryKeys(List<Integer> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 代码生成方法 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(CodeMethodConfig record);


    /**
     * 插入单条数据
     *
     * @param record 代码生成方法 信息
     * @return 插入数量
     */
    int insert(CodeMethodConfig record);
    /**
     * 条件插入单条数据
     *
     * @param record 代码生成方法 信息
     * @return 插入数量
     */
    int insertSelective(CodeMethodConfig record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 代码生成方法集合
     * @return 插入数量
     */
    int insertBatch(List<CodeMethodConfig> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 代码生成方法 信息
     * @return 修改数量
     */
    int update(CodeMethodConfig record);
    /**
     * 根据主键修改单条数据,仅修改存在数值的属性
     *
     * @param record 代码生成方法 信息
     * @return 修改数量
     */
    int updateSelective(CodeMethodConfig record);

    /**
     * 根据条件修改某些参数,仅修改存在数值的属性
     *
     * @param params  需要修改的信息
     * @param record  根据的条件信息
     * @return 修改数量
     */
    int updateParamsBySelective(CodeMethodConfig params,CodeMethodConfig record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 代码生成方法 集合
     * @return 修改数量
     */
    int updateBatch(List<CodeMethodConfig> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 代码生成方法 集合
     * @return 修改数量
     */
    int updateBatchSelective(List<CodeMethodConfig> recordList);
    /**
     * 根据条件删除数据
     *
     * @param record 删除的条件
     * @return 删除数量
     */
    int deleteBySelective(CodeMethodConfig record);
    /**
     * 根据数据值删除数据
     *
     * @param id 代码生成方法 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Integer id);


    /**
     * 根据主键集合删除数据
     *
     * @param ids 代码生成方法 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Integer> ids);

}
