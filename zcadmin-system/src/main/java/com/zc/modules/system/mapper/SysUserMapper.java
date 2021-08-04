package com.zc.modules.system.mapper;

import com.zc.modules.security.service.UserDto;
import com.zc.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author ZhangC
 * @since 2021-08-02
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select * from sys_user where username=#{name}")
    UserDto selectUserByUserName(String name);

    @Select("SELECT  m.permission FROM sys_roles_menus AS rm " +
            "LEFT JOIN sys_menu AS m ON m.menu_id = rm.menu_id " +
            "LEFT JOIN  sys_role AS r ON r.role_id = rm.role_id " +
            "LEFT JOIN sys_users_roles AS ur ON ur.role_id = r.role_id " +
            "left join sys_user as u on u.user_id=ur.user_id " +
            "where username=#{username}")
    Set<String> selectPermissionByUsername(String username);
}
