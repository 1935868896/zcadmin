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
 * 数据字典表 sys_dict
 *
 * @author zhangc
 * @date 2021-10-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="数据字典",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysDict extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "dict_id",type = IdType.AUTO)
    @ApiModelProperty(value="ID",name="dictId")
    private Long dictId;

    @ApiModelProperty(value="字典名称",name="name")
    private String name;

    @ApiModelProperty(value="描述",name="description")
    private String description;

}
