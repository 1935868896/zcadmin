package com.zc.modules.system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangC
 * @create 2021-08-30-14:05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysMenu {
    private String path;
    private String component;
    private String name;
    private Map<String,String> meta;
    private String redirect;
    private List<SysMenu> children;


}
