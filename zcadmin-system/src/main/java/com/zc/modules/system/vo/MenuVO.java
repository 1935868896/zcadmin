package com.zc.modules.system.vo;

import com.zc.modules.system.entity.Menu;
import lombok.*;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-09-01-14:27
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO extends Menu {

    List<MenuVO> children;
}
