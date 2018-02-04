package com.zhang.colas.blog.mapper;

import com.zhang.colas.blog.entity.BlogTag;

public interface BlogTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogTag record);

    int insertSelective(BlogTag record);

    BlogTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogTag record);

    int updateByPrimaryKey(BlogTag record);
}