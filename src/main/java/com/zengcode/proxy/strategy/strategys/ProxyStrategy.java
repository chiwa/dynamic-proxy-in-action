package com.zengcode.proxy.strategy.strategys;

import java.lang.reflect.Method;

public interface ProxyStrategy {
    Object apply(Method method, Object[] args, Object target) throws Throwable;
}
