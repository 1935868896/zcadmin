package com.zc.modules.quartz;

/**
 * @author ZhangC
 * @create 2021-08-12-11:11
 */
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SchedulerQuartzJob implements Job {
    //@Autowired
    //private MailJobMapper mailJobMapper;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String word = dataMap.getString("word"); //通过此我们可以获得前面传来的参数

        System.out.println("学习使用quartz"+word);
        //mailJobMapper.selsectByPrimaryKey(1);
    }
}