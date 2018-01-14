package com.zhang.colas.sns.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zxk
 * @date 2018-1-13 13:14:06
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class CommonDatasourceConfig {

}
