package com.zengcode.proxy.example1;


public class Main {
    public static void main(String[] args) throws Exception {
        HelloServiceProxy proxy = new HelloServiceProxy();
        Object result = proxy.invoke(
                HelloServiceImpl.class,
                "sayHello",
                new Class[]{String.class},
                new Object[]{"Pea"}
        );

        System.out.println(result);
    }
}