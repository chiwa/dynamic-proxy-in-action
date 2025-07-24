package com.zengcode.proxy.dynamic;

public class HelloServiceImpl implements HelloService {
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}