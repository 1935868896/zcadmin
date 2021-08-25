package com.zc.generator.mapper;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.generator.domain.TableInfo;
import com.zc.generator.entity.CodeColumnConfig;
import com.zc.generator.entity.CodeGenConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 代码生成 数据层
 *
 * @author ruoyimapper
 */
@Mapper
public interface GenMapper {
    /**
     * 查询ry数据库表信息
     *
     * @param tableInfo 表信息
     * @return 数据库表列表
     */
    List<TableInfo> selectTableList(TableInfo tableInfo);

    IPage<TableInfo> selectTablePage(TableInfo tableInfo, Page page);


    /**
     * 根据表名称查询信息
     *
     * @param tableName 表名称
     * @return 表信息
     */
    CodeGenConfig selectTableByName(String tableName);

    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    List<CodeColumnConfig> selectTableColumnsByName(String tableName);
}
