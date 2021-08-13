package com.zc.modules.quartz;

/**
 * @author ZhangC
 * @create 2021-08-12-11:11
 */

import com.zc.modules.quartz.entity.QuartzJob;
import com.zc.modules.quartz.entity.QuartzLog;
import com.zc.modules.quartz.service.QuartzJobService;
import com.zc.modules.quartz.service.QuartzLogService;
import com.zc.modules.thread.ThreadPoolExecutorUtil;
import com.zc.utils.ThrowableUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class SchedulerQuartzJob implements Job {
    //这里只能使用@Autowired的方式,不能使用 lombok提供的方法
    @Autowired
    private QuartzJobService quartzJobService;
    @Autowired
    private  QuartzLogService quartzLogService;
    @Autowired
    private  QuartzScheduleHandle quartzScheduleHandle;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
//        String word = dataMap.getString("word"); //通过此我们可以获得前面传来的参数
        QuartzJob quartzJob = (QuartzJob) dataMap.get("job");
        log.info("开始执行quartzJob:" + quartzJob.getJobName());
        ThreadPoolExecutor quartzThreadPool = ThreadPoolExecutorUtil.getQuartzThreadPool();
        QuartzLog quartzLog = QuartzLog.builder()
                .beanName(quartzJob.getBeanName())
                .cronExpression(quartzJob.getCronExpression())
                .jobName(quartzJob.getJobName())
                .methodName(quartzJob.getMethodName())
                .params(quartzJob.getParams())
                .build();
        Long preTime = System.currentTimeMillis();
        try {
            Future<Object> future = quartzThreadPool.submit(new QuartzRunnable(
                    quartzJob.getBeanName(),
                    quartzJob.getMethodName(),
                    quartzJob.getParams()
            ));
            future.get();
            quartzLog.setIsSuccess(true);
            quartzLog.setTime(System.currentTimeMillis() - preTime);

        } catch (Exception e) {
            log.error("quartz任务执行失败:" + quartzJob.getJobName());
            //这里执行失败一次后,将这个任务暂停
            quartzJob.setIsPause(true);
            if (quartzJob.getPauseAfterFailure() != null && quartzJob.getPauseAfterFailure()) {
                quartzScheduleHandle.pauseJob(quartzJob);
                quartzJobService.updateByPrimaryKey(quartzJob);
            }
            quartzLog.setIsSuccess(false);
            quartzLog.setExceptionDetail(ThrowableUtil.getStackTrace(e));
            quartzLog.setTime(System.currentTimeMillis() - preTime);
            e.printStackTrace();
        } finally {
            quartzLogService.insert(quartzLog);
        }
    }
}