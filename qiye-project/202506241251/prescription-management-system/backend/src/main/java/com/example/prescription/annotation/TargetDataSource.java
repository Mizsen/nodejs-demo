package com.example.prescription.annotation;

import java.lang.annotation.*;

// 定义自定义注解 @Target 表示可以应用的地方，@Retention 表示注解保留策略
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value(); // 数据源名称，例如 "primary" 或 "slave"
}