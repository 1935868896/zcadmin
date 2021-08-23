package com.zc.generator.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;


import com.zc.generator.entity.ColumnConfig;
import com.zc.generator.entity.GenConfig;
import org.apache.velocity.VelocityContext;

import java.util.*;

/**
 * 代码生成器 工具类
 *
 * @author ruoyi
 */

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



    public static void initColumsConfig(List<ColumnConfig> columns) {
        // 列信息
        for (ColumnConfig column : columns) {
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

            column.setFormShow(true);
            column.setSearchShow(true);
            column.setListShow(true);

            column.setFormType("text");
            column.setQueryType("=");
            column.setNotNull(false);
            column.setRemark(column.getColumnComment());

        }
    }

    public static void initGenConfig(GenConfig genConfig) {
        genConfig.setAuthor("zhangc");
        String packageName="com.zc.modules.quartz";
        genConfig.setPack(packageName);
        genConfig.setPrefix(null);
        genConfig.setApiAlias(genConfig.getTableComment());

        int lastIndex = packageName.lastIndexOf('.');
        genConfig.setBasePack(StrUtil.sub(packageName, 0, lastIndex));

    }


    public static void handleGenConfig(GenConfig genConfig) {
        String packageName = genConfig.getPack();
        int lastIndex = packageName.lastIndexOf('.');

        genConfig.setModuleName(StrUtil.sub(packageName, 0, lastIndex));
        StringBuilder projectPath = new StringBuilder();
        projectPath.append("main/java/");
        projectPath.append(packageName.replace(".", "/"));
        projectPath.append("/");
        genConfig.setProjectPath(projectPath.toString());
        int nameLength = packageName.length();
        genConfig.setVueModuleName(StrUtil.sub(packageName, lastIndex + 1, nameLength));

        String className=tableToJava(genConfig.getTableName(), genConfig.getPrefix());
        genConfig.setClassNameFirstToUpper(className);
        genConfig.setClassnameFirstToLow(StrUtil.lowerFirst(className));
        genConfig.setVueTableName(className);
    }













    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static VelocityContext getVelocityContext(GenConfig table) {
        // java对象数据传递到模板文件vm
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tableName" , table.getTableName());
        velocityContext.put("tableComment" , replaceKeyword(table.getApiAlias()));
        velocityContext.put("primaryKey" , table.getPrimaryKey());
        velocityContext.put("className" , table.getClassNameFirstToUpper());
        velocityContext.put("classname" , table.getClassnameFirstToLow());
        velocityContext.put("moduleName" ,table.getModuleName());
        velocityContext.put("columns" , table.getColumnConfigList());
        velocityContext.put("basePackage" , table.getPack());
        velocityContext.put("package" ,table.getPack());
        velocityContext.put("author" , table.getAuthor());
        velocityContext.put("datetime" , DateUtil.today());
        //以上为通用
        //下面为
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
//        templates.add("vm/html/list.html.vm");
//        templates.add("vm/html/add.html.vm");
//        templates.add("vm/html/edit.html.vm");
//        templates.add("vm/sql/sql.vm");
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
            tableName.replaceFirst(prefix + "", "");
        }

        return StrUtil.upperFirst(StrUtil.toCamelCase(tableName));
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, GenConfig table) {
        String str = "/";
        // 小写类名
        String classname = table.getClassnameFirstToLow();
        // 大写类名
        String className = table.getClassNameFirstToUpper();
        String javaPath = table.getProjectPath();
        String mybatisPath = MYBATIS_PATH + str + table.getModuleName() + str + className;
        String htmlPath = TEMPLATES_PATH + str + table.getModuleName() + str + classname;

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

        if (template.contains("list.html.vm")) {
            return htmlPath + "/" + classname + ".html" ;
        }
        if (template.contains("add.html.vm")) {
            return htmlPath + "/" + "add.html" ;
        }
        if (template.contains("edit.html.vm")) {
            return htmlPath + "/" + "edit.html" ;
        }
        if (template.contains("sql.vm")) {
            return classname + "Menu.sql" ;
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
