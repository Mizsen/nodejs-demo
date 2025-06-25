package com.example.prescription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan("com.example.prescription.mapper")
public class PrescriptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrescriptionApplication.class, args);
    }
}