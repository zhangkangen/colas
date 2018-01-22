package com.zhang.colas.sns.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SpringProperties;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

@Configuration
public class KaptchaConfig {

    private static final String PROPERTIES_RESOURCE_LOCATION = "config/kaptcha.properties";

    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        try {
            ClassLoader cl = SpringProperties.class.getClassLoader();
            URL url = cl != null ? cl.getResource(PROPERTIES_RESOURCE_LOCATION) : ClassLoader.getSystemResource(PROPERTIES_RESOURCE_LOCATION);
            InputStream is = url.openStream();
            try {
                properties.load(is);
                Config config = new Config(properties);
                defaultKaptcha.setConfig(config);
            } finally {
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return defaultKaptcha;
    }

}
