package com.zhang.colas.blog.web;

import com.zhang.colas.blog.entity.BlogTag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("tags")
public class TagsController {

    @GetMapping("list")
    @ResponseBody
    public List<BlogTag> list(){
        return new ArrayList<BlogTag>() {
            {
                add(new BlogTag(){
                    {
                        this.setId(111111);
                        this.setName("111111");
                    }
                });
                add(new BlogTag(){
                    {
                        this.setId(2222);
                        this.setName("222222");
                    }
                });
            }
        };
    }
}
