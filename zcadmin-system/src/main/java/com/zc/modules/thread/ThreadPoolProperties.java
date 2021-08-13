package com.zc.modules.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ZhangC
 * @create 2021-08-12-16:48
 */
@Data
@Component
@ConfigurationProperties(prefix = "task.pool")
public class ThreadPoolProperties {

        private int corePoolSize;

        private int maxPoolSize;

        private int keepAliveSeconds;

        private int queueCapacity;
}
