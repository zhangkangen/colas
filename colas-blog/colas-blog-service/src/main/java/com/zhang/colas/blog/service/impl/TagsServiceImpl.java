package com.zhang.colas.blog.service.impl;

import com.zhang.colas.blog.entity.BlogTag;
import com.zhang.colas.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    @Override
    public List<BlogTag> queryListByCreateBy(Integer id) {
        BlogTag tag = new BlogTag();
        tag.setCreateBy(id);
        tag.setIsValid(true);
        //todo 获取文章的标签
        return null;
    }
}
