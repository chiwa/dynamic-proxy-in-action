package com.zengcode.aop.timed;

import org.springframework.stereotype.Component;

@Component
public class MockTimedUsageService {

    @Timed("doSomething")
    public String doSomething() throws InterruptedException {
        Thread.sleep(3000);
        return "Hello Guy!!!!";
    }
}
