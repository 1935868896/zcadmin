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
 * 系统菜单表 sys_menu
 *
 * @author zhangc
 * @date 2021-09-01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="系统菜单",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "menu_id",type = IdType.AUTO)
    @ApiModelProperty(value="ID",name="menuId")
    private Long menuId;

    @ApiModelProperty(value="上级菜单ID",name="pid")
    private Long pid;

    @ApiModelProperty(value="子菜单数目",name="subCount")
    private Integer subCount;

    @ApiModelProperty(value="菜单类型",name="type")
    private Integer type;

    @ApiModelProperty(value="菜单标题",name="title")
    private String title;

    @ApiModelProperty(value="组件名称",name="name")
    private String name;

    @ApiModelProperty(value="组件",name="component")
    private String component;

    @ApiModelProperty(value="排序",name="menuSort")
    private Integer menuSort;

    @ApiModelProperty(value="图标",name="icon")
    private String icon;

    @ApiModelProperty(value="链接地址",name="path")
    private String path;

    @ApiModelProperty(value="是否外链",name="iFrame")
    private Boolean iFrame;

    @ApiModelProperty(value="缓存",name="cache")
    private Boolean cache;

    @ApiModelProperty(value="隐藏",name="hidden")
    private Boolean hidden;

    @ApiModelProperty(value="权限",name="permission")
    private String permission;

}
