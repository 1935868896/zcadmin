package com.zc.generator.domain;

import cn.hutool.core.util.StrUtil;
import com.zc.json.JSON;
import lombok.Data;

import java.io.IOException;

/**
 * 数据库表列信息
 *  1. 字段名称 字段类型 (==>延申 java属性 sql属性) 字段描述  Java属性名 attrNameFirstToUpper Java属性名 attrNameFirstToLow
 *  2. 表名  表格是否展示, 表单是否展示,上方查询是否展示,表格的类型 ,表单的类型,查询类型
 *
 *
 */
@Data
public class ColumnInfo {
    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String dataType;

    /**
     *字段描述
     */
    private String columnComment;

    /**
     * 列配置
     */
    private ColumnConfigInfo configInfo;

    /**
     * Java属性类型 如:Integer
     */
    private String attrType;
    /**
     * sql属性类型: 如INTEGER ,VARCHAR
     */
    private String sqlType;

    /**
     * Java属性名称(第一个字母大写)，如：user_name => UserName
     */
    private String attrName;

    /**
     * Java属性名称(第一个字母小写)，如：user_name => userName
     */
    private String attrname;

    /**
     *  执行计划（包含了与索引相关的一些细节信息）
     */
    private String extra;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrname() {
        return attrname;
    }

    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }

    public void setColumnComment(String columnComment) throws IOException {
        // 根据列描述解析列的配置信息
        if (StrUtil.isNotEmpty(columnComment) && columnComment.startsWith("{")) {
            this.configInfo = JSON.unmarshal(columnComment, ColumnConfigInfo.class);
            this.columnComment = configInfo.getTitle();
        } else {
            this.columnComment = columnComment;
        }
    }
}
