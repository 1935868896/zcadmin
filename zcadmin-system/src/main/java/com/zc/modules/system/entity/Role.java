package com.zc.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;


import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 角色表 sys_role
 *
 * @author zhangc
 * @date 2021-09-01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="角色",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "role_id",type = IdType.AUTO)
    @ApiModelProperty(value="ID",name="roleId")
    private Long roleId;

    @ApiModelProperty(value="名称",name="name")
    private String name;

    @ApiModelProperty(value="角色级别",name="level")
    private Integer level;

    @ApiModelProperty(value="描述",name="description")
    private String description;

    @ApiModelProperty(value="数据权限",name="dataScope")
    private String dataScope;

}
