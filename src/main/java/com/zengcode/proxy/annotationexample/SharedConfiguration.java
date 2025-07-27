package com.zengcode.proxy.annotationexample;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
@Setter
public class SharedConfiguration {
    private ConcurrentHashMap<String, Object> configuration = new ConcurrentHashMap<>();
}
