package com.zc.modules.system.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-09-02-16:41
 */
@Data
public class UsersRolesVO {
    private Long userId;
    private List<Long> roleIds;
}
