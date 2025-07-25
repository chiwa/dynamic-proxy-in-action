package com.zengcode.proxy.strategy.strategys;

import java.lang.reflect.Method;

public class ValidationStrategy implements ProxyStrategy {
    @Override
    public Object apply(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Validation: " + method.getName());
        if (args != null && args.length > 0 && "Pea".equals(args[0])) {
            throw new RuntimeException("Rejected Pea");
        }
        return "[Validation Passed] Skipped actual method call";
    }
}