package com.zhang.colas.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private final String DEFAULT_ENCODING = "UTF-8";

    @Bean
    public HttpMessageConverter<String> messageConveter(){
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName(DEFAULT_ENCODING)
        );
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(messageConveter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //super.configureContentNegotiation(configurer);
        configurer.favorParameter(false);
    }
}
