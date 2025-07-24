package com.zengcode.proxy.example1;

import java.lang.reflect.Method;

public class HelloServiceProxy {

    public Object invoke(Class<?> invokedClass, String methodName, Class<?>[] paramTypes, Object[] args) throws Exception {
        String message = "Welcome, ";

        // ดึง Method ตามชื่อและพารามิเตอร์
        Method method = invokedClass.getMethod(methodName, paramTypes);

        //ใช้ invokedClass เพื่อสร้าง instance แทน new Object()
        Object targetInstance = invokedClass.getDeclaredConstructor().newInstance();

        // เรียก method แบบ dynamic
        Object result = method.invoke(targetInstance, args);

        return message + result;
    }
}
