package com.api.gateway.config;

import com.api.gateway.util.XmlParser;
import com.api.gateway.util.XmlParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public XmlParser xmlParser (){
        return new XmlParserImpl();
    }
}
