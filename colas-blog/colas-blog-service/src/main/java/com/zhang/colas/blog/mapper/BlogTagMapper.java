package com.zhang.colas.blog.mapper;

import com.zhang.colas.blog.entity.BlogTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogTag record);

    int insertSelective(BlogTag record);

    BlogTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogTag record);

    int updateByPrimaryKey(BlogTag record);

    List<BlogTag> selectListByModel(BlogTag tag);

    BlogTag getTagByCreateByAndName(@Param("createBy") Integer createBy,@Param("name") String name);
}