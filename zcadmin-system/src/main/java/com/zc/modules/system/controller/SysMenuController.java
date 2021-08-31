package com.zc.modules.system.controller;

import com.zc.entity.ResultResponse;
import com.zc.modules.system.entity.SysMenu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangC
 * @create 2021-08-30-14:04
 */
@RestController
@RequestMapping("sysMenu")
public class SysMenuController {
    @GetMapping
    public ResultResponse menu(){
        Map map=new HashMap();
        map.put("title","项目管理");
        map.put("icon","table");

        Map map2=new HashMap();
        map2.put("title","图书");


        SysMenu sysMenu2 = SysMenu.builder().meta(map2).
                name("图书信息").redirect("/project").path("/book-info").component("project-mange/book-info").build();
        List<SysMenu> child=new ArrayList<>();
        child.add(sysMenu2);


        SysMenu sysMenu = SysMenu.builder().meta(map).children(child).
                name("项目管理").redirect("/project").path("/project").component("Layout").build();




        List<SysMenu> list=new ArrayList<>();
        list.add(sysMenu);
        return ResultResponse.success(list);
    }

}
