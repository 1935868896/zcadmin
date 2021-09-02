package com.zc.modules.system.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-09-02-16:27
 */
@Data
public class RolesMenusVO {
    private Long roleId;
    private List<Long> menuIds;
}
