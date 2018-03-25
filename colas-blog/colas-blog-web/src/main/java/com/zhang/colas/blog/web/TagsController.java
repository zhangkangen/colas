package com.zhang.colas.blog.web;

import com.zhang.colas.blog.api.service.TagsService;
import com.zhang.colas.blog.bind.annotation.CurrentUser;
import com.zhang.colas.blog.entity.BlogTag;
import com.zhang.colas.blog.entity.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @GetMapping("list")
    @ResponseBody
    public List<BlogTag> list(@CurrentUser BlogUser user, String query) {

        List<BlogTag> tags = tagsService.queryListByCreateBy(user.getId());

        List<BlogTag> tagList = new ArrayList<>();
        if (tags.size() > 0) {
            for (BlogTag tag : tags) {
                if (tag.getName().contains(query.trim())) {
                    tagList.add(tag);
                }
            }

        }
        if (tagList.size() > 0) {
            return tagList;
        } else {
            tags.add(new BlogTag() {{
                this.setName(query);
                this.setId(new Random().nextInt(10000));
            }});

            return tags;
        }
    }
}
