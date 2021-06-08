package com.example.myShop.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LogAspect {
    private final SimpleDateFormat SDformat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss.sss");

    @Pointcut("execution(* com.example.myShop.controller..*.*(..))")
    private void LogMethod() {
    }

    @Before("LogMethod()")
    public void logBefore(JoinPoint joinPoint) {
        Date date = new Date(System.currentTimeMillis());
        System.out.println(SDformat.format(date) + "  Log before: " + joinPoint);
    }

    @AfterReturning("LogMethod()")
    public void logAfterReturning(JoinPoint joinPoint) {
        Date date = new Date(System.currentTimeMillis());
        System.out.println(SDformat.format(date) + "  Log AfterReturning: " + joinPoint);
    }
}