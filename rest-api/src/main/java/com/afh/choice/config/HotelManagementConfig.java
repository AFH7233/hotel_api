package com.afh.choice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring configuration
 * <p>
 * Class to include Spring related configurations.
 *
 * @author Andres Fuentes Hernandez
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.afh.choice")
@PropertySource("classpath:application-dev.properties")
public class HotelManagementConfig {
}
