package com.zc.modules.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ZhangC
 * @create 2021-08-13-11:02
 */
@Component
@Slf4j
public class TestTask {
    public void hello(String name){
        log.info("Hello ,"+name+": 这里是定时任务的测试");
    }
}
