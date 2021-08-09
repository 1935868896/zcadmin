package com.zc.generator.service.impl;

import cn.hutool.core.util.StrUtil;
import com.zc.config.GlobalConfig;
import com.zc.generator.domain.ColumnInfo;
import com.zc.generator.domain.TableInfo;
import com.zc.generator.mapper.GenMapper;
import com.zc.generator.service.IGenService;
import com.zc.generator.util.GenUtils;
import com.zc.generator.util.VelocityInitializer;
import com.zc.utils.CharsetKit;
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

    private final GenMapper genMapper;

    @Autowired
    public GenServiceImpl(GenMapper genMapper) {
        this.genMapper = genMapper;
    }

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

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip= new ZipOutputStream(outputStream);

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
     * @param zip 生成后的压缩包
     * @param tableName 表名
     */
    private void generatorCode(ZipOutputStream zip, String tableName) {
        // 查询表信息 : 表必须包含注释信息,否则sql语句为null
        TableInfo table = genMapper.selectTableByName(tableName);
        // 查询列信息
        List<ColumnInfo> columns = genMapper.selectTableColumnsByName(tableName);
        // 表名转换成Java属性名
        String className = GenUtils.tableToJava(table.getTableName());
        table.setClassName(className);
        table.setClassname(StrUtil.lowerFirst(className));
        // 列信息
        table.setColumns(GenUtils.transColums(columns));
        // 设置主键
        table.setPrimaryKey(table.getColumnsLast());

        VelocityInitializer.initVelocity();

        /**
         *  获取包名 : 此处 ruoyi 没有在yml文件配置,因此直接使用的默认
         *       本程序使用三种方式来获得 packageName : 传输请求,配置,默认
         */

        String packageName = GlobalConfig.getPackageName();

        String moduleName = GenUtils.getModuleName(packageName);
        VelocityContext context = GenUtils.getVelocityContext(table);

        // 获取模板列表
        List<String> templates = GenUtils.getTemplates();
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, CharsetKit.UTF8);
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(Objects.requireNonNull(GenUtils.getFileName(template, table, moduleName))));
                IOUtils.write(sw.toString(), zip, CharsetKit.UTF8);
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }
}
