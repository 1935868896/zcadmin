package com.zc.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;


/**
 * 角色菜单关联表 sys_roles_menus
 *
 * @author zhangc
 * @date 2021-09-01
 */
@Data
@ApiModel(description="角色菜单关联",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesMenus{

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value="菜单ID",name="menuId")
    private Long menuId;

    @TableId(value = "role_id",type = IdType.AUTO)
    @ApiModelProperty(value="角色ID",name="roleId")
    private Long roleId;

}
