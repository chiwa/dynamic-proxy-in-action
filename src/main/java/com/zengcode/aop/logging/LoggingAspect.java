package com.zengcode.aop.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.zengcode..controller..*(..))")
    public void beforeMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        log.info("🟡 [Before] กำลังจะเรียก: {}", methodName);
    }

    @Around("execution(* com.zengcode..controller..*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();
        log.info("🔍 Calling: {}", method);
        Object result = joinPoint.proceed();
        log.info("✅ Returned: {}", result);
        return result;
    }

    @After("execution(* com.zengcode..controller..*(..))")
    public void afterMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        log.info("🟢 [After] เรียกเสร็จแล้ว: {}", methodName);
    }
}
