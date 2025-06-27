package com.example.demo;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
/*
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,  // 排除数据源自动配置
        MybatisPlusAutoConfiguration.class  // 排除MyBatis Plus默认配置
})
@MapperScan("com.example.demo.mapper")
public class DemoApplication {
    static {
        // 解决Java 21模块系统限制
        System.setProperty("spring.xml.ignore", "true");
        System.setProperty("spring.spel.ignore", "true");
    }
    public static void main(String[] args) {

        // 解决 Java 21 模块系统限制
//        System.setProperty("spring.xml.ignore", "true");
        SpringApplication.run(DemoApplication.class, args);
    }
}
*/
@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class DemoApplication {
/*
    static {
        // Java 21 必须配置
        System.setProperty("spring.xml.ignore", "true");
        System.setProperty("spring.spel.ignore", "true");
    }
*/

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}


