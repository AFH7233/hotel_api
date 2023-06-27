package com.afh.choice.initializer;

import com.afh.choice.config.HotelManagementConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring initializer (it is needed because we're not using spring boot).
 * <p>
 * It marks the entry point which is HotelManagementConfig.
 *
 * @author Andres Fuentes Hernandez
 */
public class HotelSpringServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{HotelManagementConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
