package com.zengcode.proxy.annotationexample.annotation;

import com.zengcode.proxy.annotationexample.SharedConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

@Component
public class CounterBeanPoseProcessor implements BeanPostProcessor {

    private final SharedConfiguration sharedConfiguration;

    public CounterBeanPoseProcessor(SharedConfiguration sharedConfiguration) {
        this.sharedConfiguration = sharedConfiguration;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // วนทุกฟิลด์ใน Bean ที่กำลังจะถูกสร้าง
        for (Field field : bean.getClass().getDeclaredFields()) {
            // หาว่ามี Annotation @CounterConfig ไหม
            CounterConfig annotation = AnnotationUtils.getAnnotation(field, CounterConfig.class);

            // ถ้ามี และ field นั้นเป็นประเภท CounterGetter
            if (annotation != null && CounterGetter.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true); // เปิดให้เข้าถึง private field
                String key = annotation.key(); // ดึง key จาก annotation
                Class<?> fieldType = field.getType(); // เช่น CounterGetter.class

                // สร้าง Proxy สำหรับ field นี้
                Object proxy = Proxy.newProxyInstance(
                        fieldType.getClassLoader(),
                        new Class[]{fieldType},
                        (proxy1, method, args) -> {
                            if ("get".equals(method.getName())) {
                                Object result = sharedConfiguration.getConfiguration().get(key);
                                if (result == null) {
                                    throw new IllegalStateException("❌ There is no config for key = " + key);
                                }
                                return result;
                            }
                            throw new UnsupportedOperationException("Only get() is supported");
                        });

                // set proxy เข้า field
                try {
                    field.set(bean, proxy);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("❌ Failed to inject config proxy", e);
                }
            }
        }
        return bean;
    }
}