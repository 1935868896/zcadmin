package com.zc.modules.quartz.controller;

import com.zc.modules.quartz.QuartzSchedulerNew;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangC
 * @create 2021-08-12-11:14
 */
@RestController
@RequestMapping("/quartz")
public class QuartzApiController {
    @Autowired
    private QuartzSchedulerNew quartzSchedulerNew;

    @GetMapping("/start")
    public void startQuartzJob() {
        try {
            quartzSchedulerNew.startJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/info")
    public String getQuartzJob(String name, String group) {
        String info = null;
        try {
            info = quartzSchedulerNew.getJobInfo(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return info;
    }
    @GetMapping("/modify")
    public boolean modifyQuartzJob(String name, String group, String time) {
        boolean flag = true;
        try {
            flag = quartzSchedulerNew.modifyJob(name, group, time);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return flag;
    }
    @GetMapping(value = "/pause")
    public void pauseQuartzJob(String name, String group) {
        try {
            quartzSchedulerNew.pauseJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    @GetMapping(value = "/pauseAll")
    public void pauseAllQuartzJob() {
        try {
            quartzSchedulerNew.pauseAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    @GetMapping(value="/resumeAll")
    public void resume(){
        try {
            quartzSchedulerNew.resumeAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    @GetMapping(value = "/delete")
    public void deleteJob(String name, String group) {
        try {
            quartzSchedulerNew.deleteJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}