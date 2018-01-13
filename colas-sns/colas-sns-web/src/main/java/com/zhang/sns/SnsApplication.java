package com.zhang.sns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zxk
 * @date 2018-01-12 22:42:58
 */
@EnableAutoConfiguration
@SpringBootApplication
public class SnsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SnsApplication.class, args);
    }
}
