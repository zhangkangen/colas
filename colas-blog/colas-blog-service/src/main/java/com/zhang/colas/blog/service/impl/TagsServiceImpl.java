package com.zhang.colas.blog.service.impl;

import com.zhang.colas.blog.entity.BlogTag;
import com.zhang.colas.blog.mapper.BlogTagMapper;
import com.zhang.colas.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private BlogTagMapper tagMapper;


    @Override
    public List<BlogTag> queryListByCreateBy(Integer id) {
        BlogTag tag = new BlogTag();
        tag.setCreateBy(id);
        tag.setIsValid(true);
        return tagMapper.selectListByModel(tag);
    }
}
