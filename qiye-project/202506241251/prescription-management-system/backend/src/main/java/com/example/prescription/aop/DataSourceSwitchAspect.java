package com.example.prescription.aop;

import com.example.prescription.config.DataSourceContextHolder;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(-1)
public class DataSourceSwitchAspect {

    // 读操作（slave）
    @Around("execution(* com.example.prescription.service.*.get*(..)) || execution(* com.example.prescription.service.*.find*(..)) || execution(* com.example.prescription.service.*.list*(..)) || execution(* com.example.prescription.service.*.select*(..))")
    public Object setReadDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        DataSourceContextHolder.setDataSource("slave");
        try {
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clear();
        }
    }

    // 写操作（primary）
    @Around("execution(* com.example.prescription.service.*.save*(..)) || execution(* com.example.prescription.service.*.add*(..)) || execution(* com.example.prescription.service.*.update*(..)) || execution(* com.example.prescription.service.*.delete*(..))")
    public Object setWriteDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        DataSourceContextHolder.setDataSource("primary");
        try {
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clear();
        }
    }
}