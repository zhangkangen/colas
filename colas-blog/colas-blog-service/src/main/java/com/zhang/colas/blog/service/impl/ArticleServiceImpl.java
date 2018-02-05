package com.zhang.colas.blog.service.impl;

import com.zhang.colas.blog.entity.BlogArticle;
import com.zhang.colas.blog.mapper.BlogArticleMapper;
import com.zhang.colas.blog.service.ArticleService;
import com.zhang.colas.common.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private BlogArticleMapper articleMapper;

    @Override
    public SimpleResult save(BlogArticle blogArticle) {

        if (blogArticle.getId() != null) {
            articleMapper.updateByPrimaryKeySelective(blogArticle);
        } else {
            blogArticle.setCreateBy(-1);
            blogArticle.setPublishTime(new Date());
            //todo type和status 以后使用枚举
            blogArticle.setArticleType(1);
            blogArticle.setArticleStatus(1);
            articleMapper.insertSelective(blogArticle);
        }
        return SimpleResult.responseOk("ok");
    }
}
