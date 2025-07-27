package com.zengcode.aop.retry;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RetryAspect {
    @Around("@annotation(retry)")
    public Object retry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        int maxAttempts = retry.maxRetry();
        int attempt = 0;
        Throwable lastException = null;
        while (attempt < maxAttempts) {
            try {
                return joinPoint.proceed();
            } catch (Throwable ex) {
                lastException = ex;
                attempt++;
                log.warn("Retry {}/{} failed: {}", attempt, maxAttempts, ex.getMessage());

                // ถ้าครบ maxRetry แล้ว ยัง error → โยน Exception ออก
                if (attempt >= maxAttempts) {
                    log.error("Max retry reached. Throwing exception.");
                    throw lastException;
                }
                Thread.sleep(retry.delayMillis());
            }
        }

        throw new IllegalStateException("Unreachable retry block");
    }
}