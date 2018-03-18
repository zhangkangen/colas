package com.zhang.colas.blog.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhang.colas.blog.entity.BlogTag;
import com.zhang.colas.blog.repository.TagsRepository;
import com.zhang.colas.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.List;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public List<BlogTag> queryListByCreateBy(Integer id) {
        BlogTag tag = new BlogTag();
        tag.setCreateBy(id);
        tag.setIsValid(true);
        //todo 获取文章的标签
        return tagsRepository.findAll(Example.of(tag));
    }
}
