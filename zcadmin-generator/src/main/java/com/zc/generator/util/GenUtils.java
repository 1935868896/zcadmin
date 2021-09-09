package com.zc.generator.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;


import com.zc.generator.entity.CodeColumnConfig;
import com.zc.generator.entity.CodeGenConfig;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;

import java.util.*;

/**
 * 代码生成器 工具类
 *
 * @author ruoyi
 */
@Slf4j
public class GenUtils {



    private GenUtils(){
        throw new IllegalStateException("Utility class");
    }


    /**
     * mybatis空间路径
     */
    private static final String MYBATIS_PATH = "main/resources/mapper" ;

    /**
     * html空间路径
     */
    private static final String TEMPLATES_PATH = "main/resources/templates" ;

    /**
     * 类型转换
     */
    private static Map<String, String> javaTypeMap = new HashMap<>();



    public static void initColumsConfig(List<CodeColumnConfig> columns) {
        // 列信息
        for (CodeColumnConfig column : columns) {
            // 列名转换成Java属性名
            String attrName = StrUtil.upperFirst(StrUtil.toCamelCase(column.getColumnName()));
            column.setAttrNameFirstToUpper(attrName);
            column.setAttrNameFirstToLow(StrUtil.lowerFirst(attrName));
            column.setExtra(column.getExtra());
            String attrType = javaTypeMap.get(column.getDataType());
            column.setColumnJavaType(attrType);
            column.setColumnJdbcType(dataTypeToSqlType(column.getDataType()));
            column.setKeyType(column.getColumnKey());
            column.setColumnType(column.getDataType());

            column.setListShow(true);
            // 这里可以处理一下表单的形式
            if ("data".equals(column.getDataType())){
                column.setFormType("text");
            }else {
                column.setFormType("text");
            }

            if ("PRI".equals(column.getKeyType())){
                column.setFormShow(false);
                column.setSearchShow(false);
            }else {
                column.setFormShow(true);
                column.setSearchShow(true);
            }




            column.setQueryType("=");
            column.setNotNull(false);
            column.setRemark(column.getColumnComment());

        }
    }

    public static void initGenConfig(CodeGenConfig codeGenConfig) {
        codeGenConfig.setAuthor("zhangc");
        String packageName="com.zc.modules.quartz";
        codeGenConfig.setPack(packageName);
        codeGenConfig.setPrefix(null);
        codeGenConfig.setApiAlias(codeGenConfig.getTableComment());
        codeGenConfig.setLogicDelete(false);

        int lastIndex = packageName.lastIndexOf('.');
        int nameLength = packageName.length();

        codeGenConfig.setVueModuleName(StrUtil.sub(packageName, lastIndex + 1, nameLength));

    }


    public static void handleGenConfig(CodeGenConfig codeGenConfig) {
        String packageName = codeGenConfig.getPack();
        int lastIndex = packageName.lastIndexOf('.');
        int nameLength = packageName.length();

        StringBuilder projectPath = new StringBuilder();
        projectPath.append("main/java/");
        projectPath.append(packageName.replace(".", "/"));
        projectPath.append("/");

        String className=tableToJava(codeGenConfig.getTableName(), codeGenConfig.getPrefix());
        codeGenConfig.setClassNameFirstToUpper(className);
        codeGenConfig.setClassnameFirstToLow(StrUtil.lowerFirst(className));
        codeGenConfig.setBasePack(StrUtil.sub(packageName, 0, lastIndex));
        codeGenConfig.setModuleName(StrUtil.sub(packageName, lastIndex + 1, nameLength));
        codeGenConfig.setProjectPath(projectPath.toString());
        codeGenConfig.setVueTableName(codeGenConfig.getTableName().replaceAll("_","-"));


    }



    /**
     * 模板信息传递
     *
     * @return 模板列表
     */
    public static VelocityContext getVelocityContext(CodeGenConfig table) {
        // java对象数据传递到模板文件vm
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tableName" , table.getTableName());
        velocityContext.put("tableComment" , replaceKeyword(table.getApiAlias()));
        velocityContext.put("primaryKey" , table.getPrimaryKey());
        velocityContext.put("className" , table.getClassNameFirstToUpper());
        velocityContext.put("classname" , table.getClassnameFirstToLow());
        velocityContext.put("moduleName" ,table.getModuleName());
        velocityContext.put("columns" , table.getCodeColumnConfigList());
        velocityContext.put("basePackage" , table.getPack());
        velocityContext.put("package" ,table.getPack());
        velocityContext.put("author" , table.getAuthor());
        velocityContext.put("datetime" , DateUtil.today());
        velocityContext.put("vueTableName" , table.getVueTableName());
        velocityContext.put("vueModuleName" , table.getVueModuleName());
        if (table.getLogicDelete()&& !StringUtils.isNotBlank(table.getLogicField())){
            log.error("代码生成过程中,显示需要逻辑删除但是没有提供逻辑删除的相应字段");
            velocityContext.put("logicDelete",false);
        }else {
            velocityContext.put("logicDelete",table.getLogicDelete());
            velocityContext.put("logicField",table.getLogicField());
        }

        return velocityContext;
    }

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("vm/java/Entity.java.vm");
        templates.add("vm/java/Mapper.java.vm");
        templates.add("vm/java/Service.java.vm");
        templates.add("vm/java/ServiceImpl.java.vm");
        templates.add("vm/java/Controller.java.vm");
        templates.add("vm/xml/Mapper.xml.vm");
        templates.add("vm/vue/api.js.vm");
        templates.add("vm/vue/router.js.vm");
        templates.add("vm/vue/view.vue.vm");
        return templates;
    }

    public static String dataTypeToSqlType(String dataType){


        if ("int".equals(dataType)||"tinyint".equals(dataType)){
            return "INTEGER";
        }
        if ("datetime".equals(dataType)){
            return "TIMESTAMP";
        }
        if ("text".equals(dataType)||"longtext".equals(dataType)){
            return "VARCHAR";
        }
        return dataType.toUpperCase();

    }


    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName,String prefix) {
        if (!StrUtil.isBlankIfStr(prefix)) {
           tableName= tableName.replaceFirst(prefix + "_", "");
        }

        return StrUtil.upperFirst(StrUtil.toCamelCase(tableName));
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, CodeGenConfig table) {
        String str = "/";
        // 小写类名
        String classname = table.getClassnameFirstToLow();
        // 大写类名
        String className = table.getClassNameFirstToUpper();
        String javaPath = table.getProjectPath();
        String mybatisPath = MYBATIS_PATH + str + table.getModuleName() + str + className;
        String vuePath="vue/src";

        if (template.contains("Entity.java.vm")) {
            return javaPath + "entity" + "/" + className + ".java" ;
        }

        if (template.contains("Mapper.java.vm")) {
            return javaPath + "mapper" + "/" + className + "Mapper.java" ;
        }

        if (template.contains("Service.java.vm")) {
            return javaPath + "service" + "/" +   className + "Service.java" ;
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return javaPath + "service" + "/impl/" + className + "ServiceImpl.java" ;
        }

        if (template.contains("Controller.java.vm")) {
            return javaPath + "controller" + "/" + className + "Controller.java" ;
        }

        if (template.contains("Mapper.xml.vm")) {
            return mybatisPath + "Mapper.xml" ;
        }
        if (template.contains("api.js.vm")) {
            return vuePath+"/api/"+table.getVueModuleName()+"/"+table.getVueTableName()+".js";
        }
        if (template.contains("router.js.vm")) {
            return vuePath + "/router/modules/" +table.getVueModuleName() + ".js" ;
        }
        if (template.contains("view.vue.vm")) {
            return vuePath+"/views/"+table.getVueModuleName()+"/"+table.getVueTableName()+".vue";
        }


        return null;
    }



    private static String replaceKeyword(String keyword) {
        return keyword.replaceAll("(?:表|信息|管理)", "");
    }

    static {
        String string = "String";
        String integer = "Integer";
        String date = "Date";
        javaTypeMap.put("tinyint" , integer);
        javaTypeMap.put("smallint" , integer);
        javaTypeMap.put("mediumint" , integer);
        javaTypeMap.put("int" , integer);
        javaTypeMap.put("number", integer);
        javaTypeMap.put("integer" , "integer");
        javaTypeMap.put("bigint" , "Long");
        javaTypeMap.put("float" , "Float");
        javaTypeMap.put("double" , "Double");
        javaTypeMap.put("decimal" , "BigDecimal");
        javaTypeMap.put("bit" , "Boolean");
        javaTypeMap.put("char" , string);
        javaTypeMap.put("varchar" , string);
        javaTypeMap.put("varchar2", string);
        javaTypeMap.put("tinytext" , string);
        javaTypeMap.put("text" , string);
        javaTypeMap.put("mediumtext" , string);
        javaTypeMap.put("longtext" , string);
        javaTypeMap.put("time" , date);
        javaTypeMap.put("date" , date);
        javaTypeMap.put("datetime" , date);
        javaTypeMap.put("timestamp" , date);
    }
}
