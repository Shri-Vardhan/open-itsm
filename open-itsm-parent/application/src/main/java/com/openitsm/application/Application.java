package com.openitsm.application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "com.openitsm")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}