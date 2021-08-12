package com.zc.generator.service;


import com.zc.generator.domain.GenBaseInfo;
import com.zc.generator.domain.TableInfo;

import java.util.List;

/**
 * 代码生成 服务层
 *
 * @author ruoyi
 */
public interface IGenService {
    /**
     * 查询ry数据库表信息
     *
     * @param tableInfo 表信息
     * @return 数据库表列表
     */
    List<TableInfo> selectTableList(TableInfo tableInfo);

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    byte[] generatorCode(String tableName, GenBaseInfo genBaseInfo);

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    byte[] generatorCode(String[] tableNames,GenBaseInfo genBaseInfo);
}
