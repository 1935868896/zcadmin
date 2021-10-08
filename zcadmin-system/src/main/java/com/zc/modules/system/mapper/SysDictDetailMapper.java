package com.zc.modules.system.mapper;

import com.zc.modules.system.entity.SysDictDetail;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典详情 数据层
 *
 * @author zhangc
 * @date 2021-09-30
 */
@Mapper
public interface SysDictDetailMapper extends BaseMapper<SysDictDetail> {


    /**
     * 根据条件,查询数据字典详情列表
     *
     * @param record 数据字典详情信息
     * @return 数据字典详情集合
     */
    List<SysDictDetail> selectListByParam(SysDictDetail record);

    /**
     * 根据条件,分页查询数据字典详情列表
     *
     * @param record 数据字典详情信息
     * @param page mybatis-plus 分页对象
     * @return 数据字典详情集合
     */
    IPage<SysDictDetail> selectPageByParam(SysDictDetail record, Page page);

    /**
     * 插入单条数据
     *
     * @param record 数据字典详情 信息
     * @return 插入数量
     */
    int insertOne(SysDictDetail record);

        /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 数据字典详情 信息
     * @return 修改数量
     */
    int updateById(SysDictDetail record);
    /**
     * 根据数据值删除数据
     *
     * @param id 数据字典详情 主键
     * @return 删除数量
     */
    int deleteById(Long id);
}
