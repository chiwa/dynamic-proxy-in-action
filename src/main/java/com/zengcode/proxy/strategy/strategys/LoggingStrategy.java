package com.zengcode.proxy.strategy.strategys;

import java.lang.reflect.Method;

public class LoggingStrategy implements ProxyStrategy {
    @Override
    public Object apply(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("[Log] Call: " + method.getName());
        return "[Just Logging] Skipped actual method call";
    }
}