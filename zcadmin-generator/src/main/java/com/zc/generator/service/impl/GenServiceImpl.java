package com.zc.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.generator.domain.TableInfo;
import com.zc.generator.entity.CodeColumnConfig;
import com.zc.generator.entity.CodeGenConfig;
import com.zc.generator.entity.CodeMethodConfig;
import com.zc.generator.entity.CodeSysDictDetail;
import com.zc.generator.mapper.*;
import com.zc.generator.service.IGenService;
import com.zc.generator.util.GenUtils;
import com.zc.generator.util.VelocityInitializer;
import com.zc.generator.vo.GenConfigVO;
import com.zc.utils.CharsetKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成 服务层处理
 *
 * @author ruoyi
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GenServiceImpl implements IGenService {
    private final GenMapper genMapper;
    private final CodeGenConfigMapper codeGenConfigMapper;
    private final CodeColumnConfigMapper columnConfigMapper;
    private final CodeMethodConfigMapper codeMethodConfigMapper;
    private final CodeSysDictMapper codeSysDictMapper;

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
        CodeGenConfig tableInfo = genMapper.selectTableByName(tableName);
        List<CodeColumnConfig> columnInfos = genMapper.selectTableColumnsByName(tableName);


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
        CodeGenConfig codeGenConfig = codeGenConfigMapper.selectOneBySelective(CodeGenConfig.builder().tableName(tableName).build());
        List<CodeColumnConfig> codeColumnConfigs = new ArrayList<>();
        List<CodeMethodConfig> codeMethodConfigs=new ArrayList<>();
        /**
         * 获取 表配置 和列配置对象 如果不存在的话就初始化创建
         */
        if (codeGenConfig == null) {
            initGenConfig(codeGenConfig, codeColumnConfigs, codeMethodConfigs,tableName);
        } else {
            codeColumnConfigs = columnConfigMapper.selectListBySelective(CodeColumnConfig.builder().tableName(tableName).build());
            codeMethodConfigs=codeMethodConfigMapper.selectListBySelective(CodeMethodConfig.builder().tableName(tableName).build());
        }

        GenUtils.handleGenConfig(codeGenConfig);
        codeGenConfig.setCodeColumnConfigList(codeColumnConfigs);

        // 这里对codeMethodConfig进行过滤,排除掉不需要代码生成的方法
        codeMethodConfigs=codeMethodConfigs.stream().filter(v->v.getIsGenerator()).collect(Collectors.toList());

        codeGenConfig.setCodeMethodConfigList(codeMethodConfigs);
        CodeColumnConfig primayConfig = new CodeColumnConfig();
        for (CodeColumnConfig config : codeColumnConfigs) {
            if ("PRI".equals(config.getKeyType())) {
                primayConfig = config;
            }
            if (StringUtils.isNotEmpty(config.getDictName())){
            config.setDictDetailList(codeSysDictMapper.selectDictDetail(config.getDictName()));
        }
        }
        codeGenConfig.setPrimaryKey(primayConfig);


        VelocityInitializer.initVelocity();
        VelocityContext context = GenUtils.getVelocityContext(codeGenConfig);
        // 获取模板列表
        List<String> templates = GenUtils.getTemplates();
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, CharsetKit.UTF8);
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(Objects.requireNonNull(GenUtils.getFileName(template, codeGenConfig))));
                IOUtils.write(sw.toString(), zip, CharsetKit.UTF8);
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + codeGenConfig.getTableName(), e);
            }
        }
    }


    @Override
    public void syncColumnConfig(String tableName) {

        columnConfigMapper.deleteByTableName(tableName);
        List<CodeColumnConfig> codeColumnConfigs = genMapper.selectTableColumnsByName(tableName);
        GenUtils.initColumsConfig(codeColumnConfigs);
        columnConfigMapper.insertBatch(codeColumnConfigs);
    }

    //对表配置进行初始化
    @Transactional
    public GenConfigVO initGenConfig(CodeGenConfig codeGenConfig, List<CodeColumnConfig> codeColumnConfigs, List<CodeMethodConfig> codeMethodConfigs,String tableName) {
        codeGenConfig = genMapper.selectTableByName(tableName);
        GenUtils.initGenConfig(codeGenConfig);
        codeGenConfigMapper.insert(codeGenConfig);

        codeColumnConfigs = genMapper.selectTableColumnsByName(tableName);
        GenUtils.initColumsConfig(codeColumnConfigs);
        columnConfigMapper.insertBatch(codeColumnConfigs);

        GenUtils.initMethodConfig(codeMethodConfigs,tableName);
        codeMethodConfigMapper.insertBatch(codeMethodConfigs);


        return GenConfigVO.builder().codeGenConfig(codeGenConfig).columnConfigList(codeColumnConfigs).codeMethodConfigs(codeMethodConfigs).build();
    }
}
