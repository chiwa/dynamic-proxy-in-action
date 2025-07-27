package com.zengcode.aop;

import com.zengcode.proxy.annotationexample.SharedConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CounterConfigAspect {
    private final SharedConfiguration sharedConfiguration;
    public CounterConfigAspect(SharedConfiguration sharedConfiguration) {
        this.sharedConfiguration = sharedConfiguration;
    }
    @Around("@annotation(counterConfig)")
    public Object handleCounterConfig(ProceedingJoinPoint joinPoint,
                                      CounterConfig counterConfig) throws Throwable {
        String key = counterConfig.key();
        Object value = sharedConfiguration.getConfiguration().get(key);
        if (value == null) {
            throw new IllegalStateException("❌ No config for key: " + key);
        }
        log.info("Inject config from key: {} -> {}", key, value);
        return value; // ไม่เรียก method จริงเลย
    }
}