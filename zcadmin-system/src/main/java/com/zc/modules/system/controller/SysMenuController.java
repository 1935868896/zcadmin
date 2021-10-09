package com.zc.modules.system.controller;

import com.zc.entity.ResultResponse;
import com.zc.modules.system.entity.SysMenu;
import com.zc.modules.system.service.MenuService;
import com.zc.modules.system.vo.MenuVO;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SysMenuController {
    private final MenuService menuService;

    @GetMapping
    public ResultResponse menu() {
        List<SysMenu> result = menuService.selectSysMenu();
        result=new ArrayList<>();
        return ResultResponse.success(result);
    }

}
