package com.zhang.colas.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.colas.blog.api.service.ArticleService;
import com.zhang.colas.blog.entity.BlogArticle;
import com.zhang.colas.blog.entity.BlogArticleTag;
import com.zhang.colas.blog.entity.BlogTag;
import com.zhang.colas.blog.entity.BlogUser;
import com.zhang.colas.blog.mapper.BlogArticleMapper;
import com.zhang.colas.blog.mapper.BlogArticleTagMapper;
import com.zhang.colas.blog.mapper.BlogTagMapper;
import com.zhang.colas.blog.mapper.BlogUserMapper;
import com.zhang.colas.common.PageParams;
import com.zhang.colas.common.SimpleResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private BlogArticleMapper articleMapper;
    @Autowired
    private BlogTagMapper tagMapper;
    @Autowired
    private BlogArticleTagMapper articleTagMapper;
    @Autowired
    private BlogUserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlogArticle record) {
        return articleMapper.insert(record);
    }

    @Override
    public int insertSelective(BlogArticle record) {
        record.setCreateTime(new Date());
        return articleMapper.insertSelective(record);
    }

    @Override
    public BlogArticle selectByPrimaryKey(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BlogArticle record) {
        return articleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(BlogArticle record) {
        return articleMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(BlogArticle record) {
        return articleMapper.updateByPrimaryKey(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SimpleResult save(BlogArticle article, String tagNames, BlogUser user) {

        if (article.getId() != null) {

            articleMapper.updateByPrimaryKeySelective(article);

            Integer count = articleTagMapper.removeAllByArticleId(article.getId());
        } else {
            article.setCreateBy(user.getId());
            article.setCreateTime(new Date());
            article.setPublishTime(new Date());

            //todo 需要判断是否是定时发布
            article.setArticleStatus(1);
            articleMapper.insertSelective(article);
        }

        List<BlogTag> tagList = new ArrayList<>();

        String[] tagNameArr = StringUtils.split(tagNames, ",");
        for (String name :
                tagNameArr) {
            BlogTag addTag = tagMapper.getTagByCreateByAndName(user.getId(), name);
            if (addTag == null) {
                addTag = new BlogTag();
                addTag.setName(name);
                addTag.setCreateBy(user.getId());
                addTag.setCreateTime(new Date());

                tagMapper.insertSelective(addTag);
            }
            tagList.add(addTag);
        }

        BlogArticleTag saveArticleTag;
        for (BlogTag tag :
                tagList) {
            saveArticleTag = new BlogArticleTag();
            saveArticleTag.setArticleId(article.getId());
            saveArticleTag.setTagId(tag.getId());
            saveArticleTag.setCreateBy(user.getId());
            saveArticleTag.setCreateTime(new Date());

            articleTagMapper.insertSelective(saveArticleTag);
        }

        return SimpleResult.responseOk("ok");
    }

    @Override
    public PageInfo<BlogArticle> selectArticleListPage(PageParams pageParams) {
        PageInfo<BlogArticle> pageInfo = PageHelper
                .startPage(1, pageParams.getLimit())
                .doSelectPageInfo(() ->
                {
                    List<BlogArticle> list = articleMapper.queryFeedList();
                    for (BlogArticle article :
                            list) {
                        article.setAuthor( userMapper.selectByPrimaryKey(article.getCreateBy()));

                        //todo
                        List<BlogArticleTag> articleTags = articleTagMapper.selectTagsByArticleId(article.getId());
                        List<BlogTag> tags = new ArrayList<>();
                        for (BlogArticleTag articleTag :
                                articleTags) {
                           tags.add( tagMapper.selectByPrimaryKey(articleTag.getTagId()));
                        }
                        article.setTags(tags);
                    }
                });
        return pageInfo;
    }

    @Override
    public List<BlogArticle> selectShallPublishArticleList(BlogArticle queryArticle) {
        return articleMapper.selectShallPublishArticleList(queryArticle);
    }
}
