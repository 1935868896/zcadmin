package com.zc.modules.project.mapper;

import com.zc.modules.project.entity.BookInfo;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图书 数据层
 *
 * @author zhangc
 * @date 2021-09-09
 */
@Mapper
public interface BookInfoMapper extends BaseMapper<BookInfo> {

    /**
     * 查询图书信息
     *
     * @param id 图书ID
     * @return 图书信息
     */
    BookInfo selectByPrimaryKey(Integer id);


    /**
     * 根据条件,查询图书列表
     *
     * @param record 图书信息
     * @return 图书集合
     */
    List<BookInfo> selectListBySelective(BookInfo record);

    /**
     * 根据条件,查询第一个图书对象(一般用于条件可以确定唯一数据)
     *
     * @param record 图书信息
     * @return 图书
     */
     BookInfo selectOneBySelective(BookInfo record);

    /**
     * 根据条件,分页查询图书列表
     *
     * @param record 图书信息
     * @param page mybatis-plus 分页对象
     * @return 图书集合
     */
    IPage<BookInfo> selectPageBySelective(BookInfo record, Page page);
    /**
     * 根据主键集合,批量查询图书列表
     *
     * @param ids 图书主键List集合
     * @return 图书集合
     */
    List<BookInfo> selectByPrimaryKeys(List<Integer> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 图书 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(BookInfo record);


    /**
     * 插入单条数据
     *
     * @param record 图书 信息
     * @return 插入数量
     */
    int insert(BookInfo record);
    /**
     * 条件插入单条数据
     *
     * @param record 图书 信息
     * @return 插入数量
     */
    int insertSelective(BookInfo record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 图书集合
     * @return 插入数量
     */
    int insertBatch(List<BookInfo> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 图书 信息
     * @return 修改数量
     */
    int update(BookInfo record);
    /**
     * 根据主键修改单条数据,仅修改存在数值的属性
     *
     * @param record 图书 信息
     * @return 修改数量
     */
    int updateSelective(BookInfo record);

    /**
     * 根据条件修改某些参数,仅修改存在数值的属性
     *
     * @param params  需要修改的信息
     * @param record  根据的条件信息
     * @return 修改数量
     */
    int updateParamsBySelective(BookInfo params,BookInfo record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 图书 集合
     * @return 修改数量
     */
    int updateBatch(List<BookInfo> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 图书 集合
     * @return 修改数量
     */
    int updateBatchSelective(List<BookInfo> recordList);
    /**
     * 根据条件删除数据
     * 逻辑删除      *
     * @param record 删除的条件
     * @return 删除数量
     */
    int deleteBySelective(BookInfo record);
    /**
     * 根据数据值删除数据
     * 逻辑删除      *
     * @param id 图书 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Integer id);


    /**
     * 根据主键集合删除数据
     * 逻辑删除      * @param ids 图书 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Integer> ids);

}
