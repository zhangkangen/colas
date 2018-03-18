package com.zhang.colas.blog.service;

import com.zhang.colas.blog.entity.BlogArticle;
import com.zhang.colas.blog.entity.BlogUser;
import com.zhang.colas.common.SimpleResult;

import java.util.List;

/**
 * @author zxk
 * @date 2018-02-04 23:11:27
 */
public interface ArticleService {

    SimpleResult save(BlogArticle blogArticle, String tagNames, BlogUser user);

    BlogArticle findOne(Integer id);

    BlogArticle save(BlogArticle article);

    void doArticlePublishJob();

}
