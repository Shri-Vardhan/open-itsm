package com.openitsm.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.openitsm")
@EnableJpaRepositories(basePackages = "com.openitsm")
@EntityScan(basePackages = "com.openitsm")

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}