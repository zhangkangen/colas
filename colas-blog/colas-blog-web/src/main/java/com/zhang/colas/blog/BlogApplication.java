package com.zhang.colas.blog;

import com.zhang.colas.blog.config.Utf8EncodingFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 博客系统
 * @author zxk
 * @date 2018-02-04 15:54:56
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.zhang.colas.blog.mapper")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegist(){
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new Utf8EncodingFilter());
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }
}
