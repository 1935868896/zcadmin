package com.zc.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;


/**
 * 用户角色关联表 sys_users_roles
 *
 * @author zhangc
 * @date 2021-09-01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="用户角色关联",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersRoles extends BaseEntity{

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value="用户ID",name="userId")
    private Long userId;

    @TableId(value = "role_id",type = IdType.AUTO)
    @ApiModelProperty(value="角色ID",name="roleId")
    private Long roleId;

}
