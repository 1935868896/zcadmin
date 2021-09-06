package com.zc.tool.entity;

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
 * 本地存储表 tool_local_storage
 *
 * @author zhangc
 * @date 2021-09-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="本地存储",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalStorage extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "storage_id",type = IdType.AUTO)
    @ApiModelProperty(value="ID",name="storageId")
    private Long storageId;

    @ApiModelProperty(value="文件真实的名称",name="realName")
    private String realName;

    @ApiModelProperty(value="文件名",name="name")
    private String name;

    @ApiModelProperty(value="后缀",name="suffix")
    private String suffix;

    @ApiModelProperty(value="路径",name="path")
    private String path;

    @ApiModelProperty(value="类型",name="type")
    private String type;

    @ApiModelProperty(value="大小",name="fileSize")
    private String fileSize;

}
