package com.zhang.colas.blog.api.service;

import com.zhang.colas.blog.entity.BlogTag;

import java.util.List;

public interface TagsService {

    List<BlogTag> queryListByCreateBy(Integer id);
}
