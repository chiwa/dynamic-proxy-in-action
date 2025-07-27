package com.zengcode.aop.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class MockRetryService {

    @Retry(maxRetry = 2, delayMillis = 3000)
    public String doSomeThing() {
        Random random = new Random();
        int number = random.nextInt(10) + 1;
        if (number == 5) {
            return "SUCCESS";
        }
        log.error("Mock Exception");
        throw new RuntimeException("Mock Exception");
    }
}
