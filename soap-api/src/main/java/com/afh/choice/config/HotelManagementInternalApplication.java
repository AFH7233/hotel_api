package com.afh.choice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.afh.choice", exclude = SecurityAutoConfiguration.class)
public class HotelManagementInternalApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelManagementInternalApplication.class, args);
    }

}
