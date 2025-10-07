package com.careplus.doctorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // بدون scanBasePackages، مثل user-service
public class DoctorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoctorServiceApplication.class, args);
    }
}
