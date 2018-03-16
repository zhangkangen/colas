package com.zhang.colas.blog.api.schedule;

import com.zhang.colas.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 文章相关的定时器
 * @author lenovo
 */
@Component
public class ArticleSchedule {

    private final static Logger LOGGER = LoggerFactory.getLogger(ArticleSchedule.class);

    @Autowired
    private ArticleService articleService;

    private final static long PUBLISH_INTERVAL = 1 * 60 * 1000;

    @Scheduled(fixedDelay = PUBLISH_INTERVAL)
    public void articlePublishJob() {

        LOGGER.info("开始执行发布文章定时器");
        articleService.doArticlePublishJob();
        LOGGER.info("执行发布文章定时器结束");
    }
}
