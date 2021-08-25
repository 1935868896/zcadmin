package com.zc.generator.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;


/**
 * 代码生成字段存储表 code_column_config
 *
 * @author Zhang C
 * @date 2021-08-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="代码生成字段存储",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeColumnConfig extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "column_id",type = IdType.AUTO)
    @ApiModelProperty(value="ID",name="columnId")
    private Long columnId;

    @ApiModelProperty(value="表名",name="tableName")
    private String tableName;

    @ApiModelProperty(value="字段名",name="columnName")
    private String columnName;

    @ApiModelProperty(value="字段类型",name="columnType")
    private String columnType;

    @ApiModelProperty(value="字段类型",name="extra")
    private String extra;

    @ApiModelProperty(value="表单是否存在",name="formShow")
    private Boolean formShow;

    @ApiModelProperty(value="表单存在的类型",name="formType")
    //文本框, 文本域 ,单选,下拉,日期
    private String formType;

    @ApiModelProperty(value="主键或者唯一键",name="keyType")
    private String keyType;

    @ApiModelProperty(value="表格是否保留",name="listShow")
    private Boolean listShow;

    @ApiModelProperty(value="不为空 前端和后端的验证",name="notNull")
    private Boolean notNull;

    @ApiModelProperty(value="查询类型",name="queryType")
    private String queryType;

    @ApiModelProperty(value="java字段类型",name="columnJavaType")
    private String columnJavaType;

    @ApiModelProperty(value="jdbc字段类型",name="columnJdbcType")
    private String columnJdbcType;

    @ApiModelProperty(value="首字母大写属性名",name="attrNameFirstToUpper")
    private String attrNameFirstToUpper;

    @ApiModelProperty(value="首字母小写属性名",name="attrNameFirstToLow")
    private String attrNameFirstToLow;

    @ApiModelProperty(value="前端查询是否保留",name="searchShow")
    private Boolean searchShow;

    @TableField(exist = false)
    private String dataType;

    @TableField(exist = false)
    private String columnComment;

    @TableField(exist = false)
    private String columnKey;

}
