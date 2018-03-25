package com.zhang.colas.blog.schedule;

import com.zhang.colas.blog.api.service.ArticleService;
import com.zhang.colas.blog.entity.BlogArticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 文章相关定时器
 */
@Component
public class ArticleSchedule {

    private final static Logger LOGGER = LoggerFactory.getLogger(ArticleSchedule.class);

    @Autowired
    private ArticleService articleService;

    public final static long PUBLISH_INTERVAL = 10 * 60 * 1000;

    @Scheduled(fixedRate = PUBLISH_INTERVAL)
    public void articlePublishJob() {
        LOGGER.info("文章定时发布定时器开始执行");
        //todo 获取要发布的定时文章
        //todo 更改文章的状态

        BlogArticle queryArticle = new BlogArticle();
        queryArticle.setPublishTime(new Date());
        queryArticle.setIsValid(true);
        queryArticle.setArticleStatus(2);

        List<BlogArticle> list = articleService.selectShallPublishArticleList(queryArticle);


        for (BlogArticle article :
                list) {
            article.setArticleStatus(1);

            articleService.updateByPrimaryKeySelective(article);
        }
        LOGGER.info("文章定时发布定时器执行结束");
    }
}
