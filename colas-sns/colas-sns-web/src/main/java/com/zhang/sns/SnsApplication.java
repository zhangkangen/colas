package com.zhang.sns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zxk
 * @date 2018-01-12 22:42:58
 */
//开启定时任务
//@EnableScheduling
@SpringBootApplication
public class SnsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SnsApplication.class, args);
    }
}
