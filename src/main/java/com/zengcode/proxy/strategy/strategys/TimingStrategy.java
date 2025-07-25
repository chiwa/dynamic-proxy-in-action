package com.zengcode.proxy.strategy.strategys;

import java.lang.reflect.Method;

public class TimingStrategy implements ProxyStrategy {
    @Override
    public Object apply(Method method, Object[] args, Object target) throws Throwable {
       long start = System.nanoTime();
        Object result = method.invoke(target, args);
        long end = System.nanoTime();
        System.out.println("[Timing] " + method.getName() + " took " + (end - start) + " ns");
        return result;
    }
}