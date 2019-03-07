package com.hamlt.springboot.scheduler;

import com.hamlt.springboot.interceptor.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncExecute {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Async
    public Future<Boolean> scheduled1() throws InterruptedException {
        Thread.sleep(5000);
        log.info("=====>>>>>scheduled2");
        return new AsyncResult<>(true);
    }
    @Async
    public Future<Boolean> scheduled2() throws InterruptedException {
        Thread.sleep(5000);
        log.info("=====>>>>>scheduled3");
        return new AsyncResult<>(true);
    }

}


