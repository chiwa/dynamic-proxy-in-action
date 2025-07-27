package com.zengcode.aop.timed;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimingAspect {
    @Around("@annotation(timed)")
    public Object measure(ProceedingJoinPoint joinPoint, Timed timed) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("⏱️ {} took {}ms", timed.value(), (end - start));
        return result;
    }
}