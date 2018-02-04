package com.zhang.colas.blog.web;

import com.zhang.colas.blog.entity.BlogArticle;
import com.zhang.colas.blog.service.ArticleService;
import com.zhang.colas.common.SimpleResult;
import com.zhang.colas.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zxk
 * @date 2018-02-04 19:19:47
 */
@Controller
@RequestMapping("article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @GetMapping({"index", ""})
    public String index() {
        return "article/index";
    }

    @GetMapping("editPage")
    public String editPage(Integer id, Model model) {

        return "article/edit_page";
    }

    @PostMapping("save")
    @ResponseBody
    public SimpleResult save(BlogArticle blogArticle){

        return articleService.save(blogArticle);
    }

}
