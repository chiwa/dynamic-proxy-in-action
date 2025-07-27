package com.zengcode.aop.client;

import com.zengcode.aop.CounterConfig;
import org.springframework.stereotype.Component;

@Component
public class CustomConfiguration {
    @CounterConfig(key = "current_counter")
    public int getCurrentCounter() { return -999; }

    @CounterConfig(key = "description")
    public String getDescription() { return null;}

}
