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
 * 定时任务日志表 sys_quartz_log
 *
 * @author Zhang C
 * @date 2021-08-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="定时任务日志",parent=BaseEntity.class)
@Builder
public class QuartzLog extends BaseEntity{

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value="ID",name="logId")
    private Long logId;

    @ApiModelProperty(value="",name="beanName")
    private String beanName;

    @ApiModelProperty(value="",name="cronExpression")
    private String cronExpression;

    @ApiModelProperty(value="",name="exceptionDetail")
    private String exceptionDetail;

    @ApiModelProperty(value="",name="isSuccess")
    private Boolean isSuccess;

    @ApiModelProperty(value="",name="jobName")
    private String jobName;

    @ApiModelProperty(value="",name="methodName")
    private String methodName;

    @ApiModelProperty(value="",name="params")
    private String params;

    @ApiModelProperty(value="",name="time")
    private Long time;

}
