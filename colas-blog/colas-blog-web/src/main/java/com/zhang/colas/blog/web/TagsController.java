package com.zhang.colas.blog.web;

import com.zhang.colas.blog.bind.annotation.CurrentUser;
import com.zhang.colas.blog.entity.BlogTag;
import com.zhang.colas.blog.entity.BlogUser;
import com.zhang.colas.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @GetMapping("list")
    @ResponseBody
    public List<BlogTag> list(@CurrentUser BlogUser user) {

        List<BlogTag> tags = tagsService.queryListByCreateBy(user.getId());
        return tags;
    }
}
