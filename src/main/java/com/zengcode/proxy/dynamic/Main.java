package com.zengcode.proxy.dynamic;

import java.lang.reflect.InvocationHandler;

public class Main {

    public static void main(String[] args) {
        HelloService target = new HelloServiceImpl();
        InvocationHandler handler = new LoggingInvocationHandler(target);
        HelloService proxy = ProxyFactory.createProxy(HelloService.class, handler);
        System.out.println(proxy.sayHello("Pea"));
    }
}
