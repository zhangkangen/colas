package com.zhang.colas.blog.web;

import com.zhang.colas.blog.entity.BlogComment;
import com.zhang.colas.common.base.BaseController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/comment")
public class CommentController extends BaseController {


    @RequestMapping("getByArticleId/{id}")
    public List<BlogComment> getByArticleId(@PathVariable Integer id){

        return new ArrayList<>();
    }
}
