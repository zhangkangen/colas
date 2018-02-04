package com.zhang.colas.blog.service;

import com.zhang.colas.blog.entity.BlogArticle;
import com.zhang.colas.common.SimpleResult;

/**
 * @author zxk
 * @date 2018-02-04 23:11:27
 */
public interface ArticleService {
    SimpleResult save(BlogArticle blogArticle);
}
