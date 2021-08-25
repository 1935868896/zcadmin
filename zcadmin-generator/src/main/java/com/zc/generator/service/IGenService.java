package com.zc.generator.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.generator.domain.GenBaseInfo;
import com.zc.generator.domain.TableInfo;
import com.zc.generator.entity.CodeColumnConfig;
import com.zc.generator.entity.CodeGenConfig;
import com.zc.generator.vo.GenConfigVO;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

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

    IPage<TableInfo> selectTablePage(TableInfo tableInfo, Page page);

    byte[] generatorCode2();

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    byte[] generatorCode(String tableName);

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    byte[] generatorCode(String[] tableNames);

    GenConfigVO initGenConfig(CodeGenConfig codeGenConfig, List<CodeColumnConfig> codeColumnConfigs, String tableName);

    void syncColumnConfig(String tableName);
    }
