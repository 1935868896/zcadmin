package com.zc.modules.project.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;


/**
 * 品牌表 pms_brand
 *
 * @author zhangc
 * @date 2021-08-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="品牌",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "brand_id",type = IdType.AUTO)
    @ApiModelProperty(value="品牌id",name="brandId")
    private Long brandId;

    @ApiModelProperty(value="品牌名",name="name")
    private String name;

    @ApiModelProperty(value="品牌logo地址",name="logo")
    private String logo;

    @ApiModelProperty(value="介绍",name="descript")
    private String descript;

    @ApiModelProperty(value="显示状态[0-不显示；1-显示]",name="showStatus")
    private Integer showStatus;

    @ApiModelProperty(value="检索首字母",name="firstLetter")
    private String firstLetter;

    @ApiModelProperty(value="排序",name="sort")
    private Integer sort;

}
