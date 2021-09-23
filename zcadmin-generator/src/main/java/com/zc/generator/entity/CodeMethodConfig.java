package com.zc.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;
/**
 * 代码生成方法表 code_method_config
 *
 * @author zhangc
 * @date 2021-09-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="代码生成方法",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeMethodConfig extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value="",name="id")
    private Integer id;

    @ApiModelProperty(value="表名",name="tableName")
    private String tableName;

    @ApiModelProperty(value="方法名",name="methodName")
    private String methodName;

    @ApiModelProperty(value="描述",name="description")
    private String description;

    @ApiModelProperty(value="是否需要权限",name="anonymous")
    private Boolean anonymous;

    @ApiModelProperty(value="是否需要日志",name="logRecord")
    private Boolean logRecord;

}
