package com.zc.modules.quartz;

/**
 * @author ZhangC
 * @create 2021-08-12-11:13
 */
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private QuartzScheduleHandle quartzScheduleHandle;
    /**
     * 初始启动quartz
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        try {
//            quartzSchedulerNew.startJob();
//            System.out.println("quartz初始化中.......");
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    }
    /**
     * 初始注入scheduler
     * @return
     * @throws SchedulerException
     */
    @Bean
    public Scheduler scheduler() throws SchedulerException{
        SchedulerFactory schedulerFactoryBean = new StdSchedulerFactory();
        return schedulerFactoryBean.getScheduler();
    }

}
