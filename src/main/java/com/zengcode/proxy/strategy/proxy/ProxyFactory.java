package com.zengcode.proxy.strategy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
public class ProxyFactory {
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> interfaceType, InvocationHandler handler) {
        return (T) Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class<?>[]{interfaceType},
                handler
        );
    }
}