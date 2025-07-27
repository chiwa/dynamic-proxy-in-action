package com.zengcode.proxy.annotationexample.camel;

import com.zengcode.proxy.annotationexample.SharedConfiguration;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class CamelRouter extends RouteBuilder {

    private static final String CURRENT_COUNTER = "current_counter";
    private static final String DESCRIPTION = "description";
    private final SharedConfiguration configuration;

    @Override
    public void configure() throws Exception {
        AtomicInteger number =  new AtomicInteger(0);
        from("timer?period=3000")
                .process(exchange -> {
                    int counter = number.getAndIncrement();
                    configuration.getConfiguration().put(CURRENT_COUNTER,counter);
                    String description = counter + " wast set at " + new Date();
                    configuration.getConfiguration().put(DESCRIPTION, description);
                    //log.info("Current counter is {}", counter);
                    //log.info("Description is {}", description);
                });
    }
}
