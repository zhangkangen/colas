package com.zhang.colas.blog.mapper;

import com.zhang.colas.blog.entity.BlogArticle;

import java.util.List;

public interface BlogArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogArticle record);

    int insertSelective(BlogArticle record);

    BlogArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogArticle record);

    int updateByPrimaryKeyWithBLOBs(BlogArticle record);

    int updateByPrimaryKey(BlogArticle record);

    List<BlogArticle> queryList();
}