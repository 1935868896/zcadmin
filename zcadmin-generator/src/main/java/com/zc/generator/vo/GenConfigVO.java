package com.zc.generator.vo;

import com.zc.generator.entity.CodeColumnConfig;
import com.zc.generator.entity.CodeGenConfig;
import com.zc.generator.service.CodeGenConfigService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-08-24-16:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenConfigVO {
    private List<CodeColumnConfig> columnConfigList;
    private CodeGenConfig codeGenConfig;
}
