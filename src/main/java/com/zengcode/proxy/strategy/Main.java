package com.zengcode.proxy.strategy;

import com.zengcode.proxy.strategy.handler.StrategyInvocationHandler;
import com.zengcode.proxy.strategy.proxy.ProxyFactory;
import com.zengcode.proxy.strategy.strategys.LoggingStrategy;
import com.zengcode.proxy.strategy.strategys.ValidationStrategy;
import com.zengcode.proxy.strategy.strategys.ProxyStrategy;
import com.zengcode.proxy.strategy.strategys.TimingStrategy;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        HelloService target = new HelloServiceImpl();
        List<ProxyStrategy> strategies = List.of(
                new LoggingStrategy(),
                new ValidationStrategy(),
                new TimingStrategy()
        );
        HelloService proxy = ProxyFactory.createProxy(
                HelloService.class,
                new StrategyInvocationHandler(target, strategies)
        );
        proxy.sayHello("Pea");
    }
}
