package com.zc.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;


import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 系统用户表 sys_user
 *
 * @author zhangc
 * @date 2021-09-01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="系统用户",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "user_id",type = IdType.AUTO)
    @ApiModelProperty(value="ID",name="userId")
    private Long userId;

    @ApiModelProperty(value="登录名(用户帐号)",name="username")
    private String username;

    @ApiModelProperty(value="用户昵称",name="nickName")
    private String nickName;

    @ApiModelProperty(value="性别",name="gender")
    private String gender;

    @ApiModelProperty(value="手机号码",name="phone")
    private String phone;

    @ApiModelProperty(value="邮箱",name="email")
    private String email;

    @ApiModelProperty(value="所属区域",name="region")
    private String region;

    @ApiModelProperty(value="使用年限",name="serviceLife")
    private String serviceLife;

    @ApiModelProperty(value="头像地址",name="avatarName")
    private String avatarName;

    @ApiModelProperty(value="头像真实路径",name="avatarPath")
    private String avatarPath;

    @ApiModelProperty(value="用户星级",name="starRating")
    private String starRating;

    @ApiModelProperty(value="密码",name="password")
    private String password;

    @ApiModelProperty(value="状态：1启用、0禁用",name="enabled")
    private Integer enabled;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="修改密码的时间",name="pwdResetTime")
    private Date pwdResetTime;



}
