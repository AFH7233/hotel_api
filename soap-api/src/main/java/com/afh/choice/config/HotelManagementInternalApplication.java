package com.afh.choice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Spring boot configuration
 * <p>
 * It establishes com.afh.choice as the base package in which it will search for beans
 * and disables security.
 *
 * @author Andres Fuentes Hernandez
 */
@SpringBootApplication(scanBasePackages = "com.afh.choice", exclude = SecurityAutoConfiguration.class)
public class HotelManagementInternalApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelManagementInternalApplication.class, args);
    }

}
