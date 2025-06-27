package com.example.demo.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Before("@annotation(dataSource)")
    public void beforeSwitch(DataSource dataSource) {
        DbContextHolder.setDbType(dataSource.value());
    }

    @After("@annotation(dataSource)")
    public void afterSwitch(DataSource dataSource) {
        DbContextHolder.clear();
    }
}