package com.afh.choice.config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointAdapter;
import org.springframework.ws.server.endpoint.adapter.PayloadEndpointAdapter;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.soap.server.endpoint.annotation.SoapActions;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Properties;

@Configuration
@EnableWs
public class SoapEndpointConfiguration extends WsConfigurerAdapter {
    private static final String NAMESPACE_URI = "http://afh.com/choice/soap";

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }
    @Bean(name = "HotelServiceEndpoint")
    public DefaultWsdl11Definition hotelServiceDefinition() {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("HotelServiceEndpoint");
        definition.setLocationUri("/ws");
        definition.setTargetNamespace(NAMESPACE_URI);
        definition.setSchema(hotelSchema());
        return definition;
    }

    @Bean
    public XsdSchema hotelSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema/hotel.xsd"));
    }

}