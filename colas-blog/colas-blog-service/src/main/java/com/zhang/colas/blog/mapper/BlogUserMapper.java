package com.zhang.colas.blog.mapper;

import com.zhang.colas.blog.entity.BlogUser;

import java.util.List;

public interface BlogUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogUser record);

    int insertSelective(BlogUser record);

    BlogUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogUser record);

    int updateByPrimaryKey(BlogUser record);

    List<BlogUser> selectListByModel(BlogUser user);
}