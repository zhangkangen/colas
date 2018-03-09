package com.zhang.colas.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 博客系统
 * @author zxk
 * @date 2018-02-04 15:54:56
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
@EntityScan("com.zhang.colas.blog.entity")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
