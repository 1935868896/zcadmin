package com.zc.modules.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import com.zc.modules.system.entity.Role;
import com.zc.modules.system.entity.User;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;


import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 系统用户表 sys_user
 *
 * @author zhangc
 * @date 2021-09-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO{


    User user;
    Set<Long> roles;

}
