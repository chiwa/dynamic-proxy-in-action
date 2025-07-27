package com.zengcode.proxy.strategy;

public class HelloServiceImpl implements HelloService {
    public String sayHello(String name) {
        return "Hello, " + name;
    }

    public String sayHell2(String name) {
        return "Hello, " + name;
    }
}