package com.zc.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;
/**
 * 第三方认证用户表 sys_user_third_auth
 *
 * @author zhangc
 * @date 2021-10-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="第三方认证用户",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUserThirdAuth extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "auth_id",type = IdType.AUTO)
    @ApiModelProperty(value="",name="authId")
    private Long authId;

    @ApiModelProperty(value="用户编号",name="userId")
    private Long userId;

    @ApiModelProperty(value="第三方用户唯一标识",name="openid")
    private String openid;

    @ApiModelProperty(value="第三方获取的access_token,校验使用",name="accessToken")
    private String accessToken;

    @ApiModelProperty(value="第三方登录类型(如微信等)",name="loginType")
    private Integer loginType;

    @ApiModelProperty(value="说明",name="authDesc")
    private String authDesc;

}
