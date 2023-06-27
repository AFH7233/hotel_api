package com.afh.choice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;


/**
 * SOAP configuration
 * <p>
 * Class to configure the SOAP client.
 *
 * @author Andres Fuentes Hernandez
 */
@Configuration
public class SoapServiceConfiguration {

    @Value("${hotel.endpoint.url}")
    private String ENDPOINT_URI;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.afh.choice.soap");
        return marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller());
        webServiceTemplate.setUnmarshaller(marshaller());
        webServiceTemplate.setDefaultUri(ENDPOINT_URI);
        return webServiceTemplate;
    }
}
