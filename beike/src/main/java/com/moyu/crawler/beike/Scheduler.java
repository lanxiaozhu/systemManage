package com.moyu.crawler.beike;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther: wishm
 * @Date: 2019/5/8 14:33
 * @Description:
 */
@Component
public class Scheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron="0 0/1 * * * ?") //每分钟执行一次
    public void statusCheck() {
        logger.info("每分钟执行一次。开始……");
        //statusTask.healthCheck();
        logger.info("每分钟执行一次。结束。");
    }

    @Scheduled(fixedRate=20000)
    public void testTasks() {
        logger.info("每20秒执行一次。开始……");
        //statusTask.healthCheck();
        logger.info("每20秒执行一次。结束。");
    }
}
