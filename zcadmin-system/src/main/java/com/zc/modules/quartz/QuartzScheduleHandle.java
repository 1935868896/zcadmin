package com.zc.modules.quartz;

/**
 * @author ZhangC
 * @create 2021-08-12-11:13
 */

import com.zc.exception.BadRequestException;
import com.zc.modules.quartz.entity.QuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Configuration
@Slf4j
public class QuartzScheduleHandle {
    private static final String JOB_NAME = "TASK_";

    // 任务调度
    @Autowired
    private Scheduler scheduler;

    /**
     * 开始执行所有任务
     */
    public void startAllJob() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();

        }
    }

    /**
     * 获取Job信息
     */
    public String getJobInfo(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
                scheduler.getTriggerState(triggerKey).name());
    }

    /**
     * 修改某个任务的执行时间
     */
    public boolean modifyJob(QuartzJob quartzJob) {
        Date date = null;
        try {
            TriggerKey triggerKey = new TriggerKey(JOB_NAME + quartzJob.getJobId());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            String oldTime = cronTrigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(quartzJob.getCronExpression())) {
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());
                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(JOB_NAME + quartzJob.getJobId())
                        .withSchedule(cronScheduleBuilder).build();
                date = scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            log.error("定时任务修改失败", e);
            throw new BadRequestException("定时任务修改失败");
        }
        return date != null;
    }

    /**
     * 暂停所有任务
     */
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    /**
     * 暂停某个任务
     */
    public void pauseJob(QuartzJob quartzJob) {
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getJobId());
            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            log.error("定时任务暂停失败", e);
            throw new BadRequestException("定时任务暂停失败");
        }
    }

    /**
     * 恢复所有任务
     */
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    /**
     * 恢复某个任务
     */
    public void resumeJob(QuartzJob quartzJob) {
        try {
            JobKey jobKey = new JobKey(JOB_NAME + quartzJob.getJobId());
            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            log.error("定时任务恢复失败", e);
            throw new BadRequestException("定时任务恢复失败");
        }
    }

    /**
     * 删除某个任务
     */
    public void deleteJob(QuartzJob quartzJob) {
        try {
            JobKey jobKey = new JobKey(JOB_NAME + quartzJob.getJobId());
            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            log.error("定时任务恢复失败", e);
            throw new BadRequestException("定时任务恢复失败");
        }
    }

    public void runJobNow(QuartzJob quartzJob) {
        try {
            JobKey jobKey = new JobKey(JOB_NAME + quartzJob.getJobId());
            scheduler.triggerJob(jobKey);
        } catch (Exception e) {
            log.error("定时任务恢复失败", e);
            throw new BadRequestException("定时任务恢复失败");
        }
    }

    public void createJob(QuartzJob quartzJob) {
        // 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
        // JobDetail 是具体Job实例
        try {


            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("job", quartzJob);

            JobDetail jobDetail = JobBuilder
                    .newJob(SchedulerQuartzJob.class)
                    .withIdentity(JOB_NAME+ quartzJob.getJobId())
                    .usingJobData(jobDataMap)
                    .build();
            // 基于表达式构建触发器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());//mailJob.getCron()
            // CronTrigger表达式触发器 继承于Trigger
            // TriggerBuilder 用于构建触发器实例
            CronTrigger cronTrigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(JOB_NAME+ quartzJob.getJobId())
                    .withSchedule(cronScheduleBuilder)
                    .build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
            if (quartzJob.getIsPause()) {
                JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getJobId());
                scheduler.pauseJob(jobKey);
            }
        } catch (Exception e) {
            log.error("定时任务创建失败", e);
            throw new BadRequestException("定时任务创建失败");
        }
    }
}
