package com.zhang.colas.sns;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author zxk
 * @date 2018-01-12 22:42:58
 */
//开启定时任务
//@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = "com.zhang.colas.sns.mapper")
public class SnsApplication {
    public static void main(String[] args) {

        //SpringApplication.run(SnsApplication.class, args);
        new SpringApplicationBuilder(SnsApplication.class)
                .properties("spring.config.name=application,config/qiniu")
                .run(args);
    }
}
