package com.zengcode.proxy.strategy.handler;

import com.zengcode.proxy.strategy.strategys.ProxyStrategy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class StrategyInvocationHandler implements InvocationHandler {
    private final Object target;
    private final List<ProxyStrategy> strategies;
    public StrategyInvocationHandler(Object target, List<ProxyStrategy> strategies) {
        this.target = target;
        this.strategies = strategies;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        for (ProxyStrategy strategy : strategies) {
            result = strategy.apply(method, args, target);
        }
        return result;
    }
}