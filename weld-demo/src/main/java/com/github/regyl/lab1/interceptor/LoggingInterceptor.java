package com.github.regyl.lab1.interceptor;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class LoggingInterceptor {
    
    @AroundInvoke
    public Object profile(InvocationContext ic) throws Exception {
        long initTime = System.nanoTime();
        try {
            return ic.proceed();
        } finally {
            long diffTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - initTime);
            log.info(ic.getMethod() + " took " + diffTime + " millis");
        }
    }
}
