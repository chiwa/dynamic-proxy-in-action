package com.zengcode.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.zengcode.proxy", "com.zengcode.aop"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}