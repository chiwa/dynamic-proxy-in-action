package com.zengcode.proxy.annotationexample.controller;

import com.zengcode.aop.client.CustomConfiguration;
import com.zengcode.aop.retry.MockRetryService;
import com.zengcode.aop.timed.MockTimedUsageService;
import com.zengcode.proxy.annotationexample.annotation.CounterConfig;
import com.zengcode.proxy.annotationexample.annotation.CounterGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/proxy")
@RequiredArgsConstructor
public class MainController {

    @CounterConfig(key ="current_counter")
    private CounterGetter<Integer> counter ;

    @CounterConfig(key ="description")
    private CounterGetter<String> description ;

    private final CustomConfiguration configuration;
    private final MockTimedUsageService mockTimedUsageService;
    private final MockRetryService mockRetryService;


    @GetMapping("/counter")
    public String getCurrentCounter() {
        return "Current counter is " + counter.get() +", Description : " + description.get();
    }

    @GetMapping("/aop/counter")
    public String getCurrentCounterAOP() throws InterruptedException {
        mockTimedUsageService.doSomething();
        return "Current counter is " + configuration.getCurrentCounter() +", Description : " + configuration.getDescription();
    }

    @GetMapping("/aop/retry")
    public String retry() throws InterruptedException {
        return mockRetryService.doSomeThing();
    }
}
