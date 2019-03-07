package com.hamlt.springboot.scheduler;

import com.hamlt.springboot.interceptor.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync        // 2.开启多线程(所有任务并发执行，如果有一个任务没有添加@Async，则其他任务会与其串行)
public class Task1 {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Scheduled(cron = "0/5 * * * * *")
    @Async
    public void scheduled() {
        log.info("=====>>>>>使用cron  {}", System.currentTimeMillis());
    }

    @Scheduled(fixedRate = 5000) //上一次开始执行时间点后5秒再次执行；
    @Async
    public void scheduled1() {
        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
    }

    @Scheduled(fixedDelay = 5000) //上一次执行完毕时间点5秒再次执行；
    @Async
    public void scheduled2() throws InterruptedException {
        Thread.sleep(5000);
        log.info("=====>>>>>fixedDelay{}", System.currentTimeMillis());
    }

    //第一次延迟1秒执行，然后在上一次执行完毕时间点3秒再次执行；
    @Scheduled(initialDelay=1000, fixedDelay=5000)
    @Async
    public void scheduled3() throws InterruptedException {
        Thread.sleep(100000);
        log.info("=====>>>>>initialDelay,fixedDelay{}", System.currentTimeMillis());
    }

}


