package com.zc.generator.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;


/**
 * 代码生成器配置表 code_gen_config
 *
 * @author Zhang C
 * @date 2021-08-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "代码生成器配置", parent = BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeGenConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "config_id", type = IdType.AUTO)
    @ApiModelProperty(value = "ID", name = "configId")
    private Long configId;

    @ApiModelProperty(value = "表名", name = "tableName")
    private String tableName;

    @ApiModelProperty(value = "作者", name = "author")
    private String author;

    @ApiModelProperty(value = "是否覆盖", name = "cover")
    private Boolean cover;

    @ApiModelProperty(value = "模块名称", name = "vueModuleName")
    private String vueModuleName;

    @ApiModelProperty(value = "至于哪个包下", name = "pack")
    private String pack;

    @ApiModelProperty(value = "前端代码生成的路径", name = "path")
    private String path;

    @ApiModelProperty(value = "前端Api文件路径", name = "apiPath")
    private String apiPath;

    @ApiModelProperty(value = "表前缀", name = "prefix")
    private String prefix;

    @ApiModelProperty(value = "接口名称", name = "apiAlias")
    private String apiAlias;

    @ApiModelProperty(value = "是否存在逻辑删除", name = "logicDelete")
    private Boolean logicDelete;
    @ApiModelProperty(value = "逻辑删除的字段", name = "logicField")
    private String logicField;


    List<CodeColumnConfig> codeColumnConfigList;

    CodeColumnConfig primaryKey;


    private String vueTableName;

    /**
     * 表描述
     */
    @ApiModelProperty(value = "表的描述")
    private String tableComment;

    private String engine;

    private String tableCollation;

    /**
     * 类名(第一个字母大写)
     */
    private String classNameFirstToUpper;

    /**
     * 类名(第一个字母小写)
     */
    private String classnameFirstToLow;

    private String basePack;

    private String moduleName;

    private String projectPath;

}
