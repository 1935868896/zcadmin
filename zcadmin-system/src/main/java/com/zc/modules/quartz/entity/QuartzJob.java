package com.zc.modules.quartz.entity;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 定时任务表 sys_quartz_job
 *
 * @author Zhang C
 * @date 2021-08-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="定时任务",parent=BaseEntity.class)
@Builder
public class QuartzJob extends BaseEntity{

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value="ID",name="jobId")
    private Long jobId;

    @ApiModelProperty(value="Spring Bean名称",name="beanName",example = "testTask")
    private String beanName;

    @ApiModelProperty(value="cron 表达式",name="cronExpression")
    private String cronExpression;

    @ApiModelProperty(value="状态：1暂停、0启用",name="isPause",example = "true")
    private Boolean isPause;

    @ApiModelProperty(value="任务名称",name="jobName",example = "test1")
    private String jobName;

    @ApiModelProperty(value="方法名称",name="methodName",example = "hello")
    private String methodName;

    @ApiModelProperty(value="参数",name="params",example = "张三")
    private String params;

    @ApiModelProperty(value="备注",name="description")
    private String description;

    @ApiModelProperty(value="负责人",name="personInCharge")
    private String personInCharge;

    @ApiModelProperty(value="报警邮箱",name="email")
    private String email;

    @ApiModelProperty(value="子任务ID",name="subTask")
    private String subTask;

    @ApiModelProperty(value="任务失败后是否暂停",name="pauseAfterFailure",example = "true")
    private Boolean pauseAfterFailure;

}
