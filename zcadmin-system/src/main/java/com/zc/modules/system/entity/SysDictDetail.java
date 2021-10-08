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
 * 数据字典详情表 sys_dict_detail
 *
 * @author zhangc
 * @date 2021-09-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="数据字典详情",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysDictDetail extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "detail_id",type = IdType.AUTO)
    @ApiModelProperty(value="ID",name="detailId")
    private Long detailId;

    @ApiModelProperty(value="字典id",name="dictId")
    private Long dictId;

    @ApiModelProperty(value="字典标签",name="label")
    private String label;

    @ApiModelProperty(value="字典值",name="value")
    private String value;

    @ApiModelProperty(value="排序",name="dictSort")
    private Integer dictSort;

}
