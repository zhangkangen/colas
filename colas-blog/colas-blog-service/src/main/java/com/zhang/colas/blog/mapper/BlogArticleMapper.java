package com.zhang.colas.blog.mapper;

import com.zhang.colas.blog.entity.BlogArticle;
import com.zhang.colas.common.PageParams;

import java.util.List;

public interface BlogArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogArticle record);

    int insertSelective(BlogArticle record);

    BlogArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogArticle record);

    int updateByPrimaryKeyWithBLOBs(BlogArticle record);

    int updateByPrimaryKey(BlogArticle record);

    List<BlogArticle> queryFeedList();

    List<BlogArticle> selectShallPublishArticleList(BlogArticle queryArticle);
}