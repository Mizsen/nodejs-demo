package com.example.prescription.aop;

import com.example.prescription.annotation.TargetDataSource;
import com.example.prescription.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceSwitchByAnnotationAspect {

    @Before("@annotation(com.example.prescription.annotation.TargetDataSource)")
    public void switchDataSource(JoinPoint point) throws NoSuchMethodException {
        // 获取当前执行的方法签名
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 获取目标对象
        Class<?> targetClass = point.getTarget().getClass();
        // 获取目标方法
        Method method = targetClass.getMethod(signature.getName(), signature.getParameterTypes());
        // 获取方法上的注解
        TargetDataSource annotation = method.getAnnotation(TargetDataSource.class);
        if (annotation != null) {
            // 设置数据源
            DataSourceContextHolder.setDataSource(annotation.value());
        }
    }

    @Before("execution(* *(..)) && @within(com.example.prescription.annotation.TargetDataSource)")
    public void switchDataSourceForClass(JoinPoint point) {
        Class<?> targetClass = point.getTarget().getClass();
        TargetDataSource annotation = targetClass.getAnnotation(TargetDataSource.class);
        if (annotation != null) {
            DataSourceContextHolder.setDataSource(annotation.value());
        }
    }
}