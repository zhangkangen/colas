package com.zhang.colas.blog.mapper;

import com.zhang.colas.blog.entity.BlogArticleTag;

public interface BlogArticleTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogArticleTag record);

    int insertSelective(BlogArticleTag record);

    BlogArticleTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogArticleTag record);

    int updateByPrimaryKey(BlogArticleTag record);

    Integer removeAllByArticleId(Integer articleId);
}