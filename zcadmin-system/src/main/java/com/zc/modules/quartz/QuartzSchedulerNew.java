package com.zc.modules.quartz;

/**
 * @author ZhangC
 * @create 2021-08-12-11:13
 */
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@Configuration
public class QuartzSchedulerNew {
    //    @Autowired
//    private MailJobMapper mailJobMapper;
    // 任务调度
    @Autowired
    private Scheduler scheduler;
    /**
     * 开始执行所有任务
     */
    public void startJob() throws SchedulerException {
        startJob(scheduler);
        scheduler.start();
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
    public boolean modifyJob(String name, String group, String time) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group)
                    .withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
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
    public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.pauseJob(jobKey);
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
    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.resumeJob(jobKey);
    }
    /**
     * 删除某个任务
     */
    public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.deleteJob(jobKey);
    }
    private void startJob(Scheduler scheduler) throws SchedulerException {
        // 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
        // JobDetail 是具体Job实例
        JobDetail jobDetail = JobBuilder
                .newJob(SchedulerQuartzJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("word", "Hello,World")//可以往ScheduleQuartzJob传参
                .build();
        // 基于表达式构建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 */3 * * * ?");//mailJob.getCron()
        // CronTrigger表达式触发器 继承于Trigger
        // TriggerBuilder 用于构建触发器实例
        CronTrigger cronTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("job1", "group1")
                .withSchedule(cronScheduleBuilder)
                .build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }
}
