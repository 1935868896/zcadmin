package com.zc.generator.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.config.GlobalConfig;
import com.zc.generator.domain.ColumnInfo;
import com.zc.generator.domain.GenBaseInfo;
import com.zc.generator.domain.TableInfo;
import com.zc.generator.entity.ColumnConfig;
import com.zc.generator.entity.GenConfig;
import com.zc.generator.mapper.ColumnConfigMapper;
import com.zc.generator.mapper.GenConfigMapper;
import com.zc.generator.mapper.GenMapper;
import com.zc.generator.service.IGenService;
import com.zc.generator.util.GenUtils;
import com.zc.generator.util.VelocityInitializer;
import com.zc.utils.CharsetKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成 服务层处理
 *
 * @author ruoyi
 */
@Service
@Slf4j
public class GenServiceImpl implements IGenService {

    @Autowired
    GenMapper genMapper;
    @Autowired
    GenConfigMapper genConfigMapper;
    @Autowired
    ColumnConfigMapper columnConfigMapper;


    /**
     * 查询ry数据库表信息
     *
     * @param tableInfo 表信息
     * @return 数据库表列表
     */
    @Override
    public List<TableInfo> selectTableList(TableInfo tableInfo) {
        return genMapper.selectTableList(tableInfo);
    }

    @Override
    public IPage<TableInfo> selectTablePage(TableInfo tableInfo, Page page) {
        return genMapper.selectTablePage(tableInfo, page);
    }

    @Override
    public byte[] generatorCode2() {
        /**
         *  生成代码
         */
        /**
         *  1.根据tablename查询所有的数据
         */
        String tableName = "bookInfo";
        GenConfig tableInfo = genMapper.selectTableByName(tableName);
        List<ColumnConfig> columnInfos = genMapper.selectTableColumnsByName(tableName);


        return new byte[0];
    }

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        // 生成代码
        generatorCode(zip, tableName);
//        IOUtils.closeQuietly(zip);
        try {
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            // 生成代码
            generatorCode(zip, tableName);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }


    /**
     * 根据表信息生成代码
     *
     * @param zip       生成后的压缩包
     * @param tableName 表名
     */
    private void generatorCode(ZipOutputStream zip, String tableName) {
        //
        List<GenConfig> genConfigs = genConfigMapper.selectListBySelective(GenConfig.builder().tableName(tableName).build());
        GenConfig genConfig=new GenConfig();
        if (genConfigs==null||genConfigs.size()<=0){
            /**
             * genConfig 初始化,将必须的一些配置预先定义好
             */
            genConfig=genMapper.selectTableByName(tableName);
            GenUtils.initGenConfig(genConfig);
            genConfigMapper.insert(genConfig);

        }else {
            genConfig=genConfigs.get(0);
        }
        GenUtils.handleGenConfig(genConfig);
        List<ColumnConfig> columnConfigs = columnConfigMapper.selectListBySelective(ColumnConfig.builder().tableName(tableName).build());
        if (columnConfigs==null||columnConfigs.size()<=0){
            /**
             * columnConfigs 初始化
             */
            columnConfigs = genMapper.selectTableColumnsByName(tableName);
            GenUtils.initColumsConfig(columnConfigs);
            columnConfigMapper.insertBatch(columnConfigs);
        }

        genConfig.setColumnConfigList(columnConfigs);
        ColumnConfig primayConfig=new ColumnConfig();
        for (ColumnConfig config : columnConfigs) {


            if ("PRI".equals(config.getKeyType())){
                primayConfig=config;
            }
        }
        genConfig.setPrimaryKey(primayConfig);


        VelocityInitializer.initVelocity();

        VelocityContext context = GenUtils.getVelocityContext(genConfig);

        // 获取模板列表
        List<String> templates = GenUtils.getTemplates();
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, CharsetKit.UTF8);
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(Objects.requireNonNull(GenUtils.getFileName(template,genConfig))));
                IOUtils.write(sw.toString(), zip, CharsetKit.UTF8);
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + genConfig.getTableName(), e);
            }
        }
    }
}
