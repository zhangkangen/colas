package com.zhang.colas.blog.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhang.colas.blog.entity.BlogArticle;
import com.zhang.colas.blog.entity.BlogArticleTag;
import com.zhang.colas.blog.entity.BlogTag;
import com.zhang.colas.blog.entity.BlogUser;
import com.zhang.colas.blog.repository.ArticleRepository;
import com.zhang.colas.blog.repository.ArticleTagRepository;
import com.zhang.colas.blog.service.ArticleService;
import com.zhang.colas.common.SimpleResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleTagRepository articleTagRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SimpleResult save(BlogArticle article, String tagNames, BlogUser user) {

        if (article.getId() != null) {

            articleRepository.save(article);

        } else {
            article.setCreateBy(user.getId());
            article.setCreateTime(new Date());
            article.setPublishTime(new Date());

            //todo 需要判断是否是定时发布
            article.setArticleStatus(1);
            articleRepository.save(article);
        }

        List<BlogTag> tagList = new ArrayList<>();

        String[] tagNameArr = StringUtils.split(tagNames, ",");
        for (String name :
                tagNameArr) {
            //todo 保存文章的标签
        }

        BlogArticleTag saveArticleTag;
        for (BlogTag tag :
                tagList) {
            saveArticleTag = new BlogArticleTag();
            saveArticleTag.setArticleId(article.getId());
            saveArticleTag.setTagId(tag.getId());
            saveArticleTag.setCreateBy(user.getId());
            saveArticleTag.setCreateTime(new Date());

            articleTagRepository.save(saveArticleTag);
        }

        return SimpleResult.responseOk("ok");
    }

    @Override
    public BlogArticle findOne(Integer id) {
        return articleRepository.findOne(id);
    }

    @Override
    public BlogArticle save(BlogArticle article) {
        return articleRepository.save(article);
    }

    @Override
    public void doArticlePublishJob() {
        List<BlogArticle> articleList = articleRepository.queryShallPublishArticleList(new BlogArticle() {
            {
                setPublishTime(new Date());
            }
        });

        LOGGER.info("要发布的文章数:" + articleList.size());
        if (articleList.size() > 0) {
            for (BlogArticle article :
                    articleList) {
                article.setArticleStatus(1);
            }
            articleRepository.save(articleList);
        }

    }
}
