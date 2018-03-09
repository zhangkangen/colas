package com.zhang.colas.blog.web;

import com.google.gson.Gson;
import com.hankcs.hanlp.HanLP;
import com.zhang.colas.blog.bind.annotation.CurrentUser;
import com.zhang.colas.blog.entity.BlogArticle;
import com.zhang.colas.blog.entity.BlogUser;
import com.zhang.colas.blog.enums.ArticleTypeEnum;
import com.zhang.colas.blog.service.ArticleService;
import com.zhang.colas.common.PageParams;
import com.zhang.colas.common.SimpleResult;
import com.zhang.colas.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String index(PageParams pageParams, Model model) {

        /*
        PageInfo<BlogArticle> pageInfo = articleService.selectArticleListPage(pageParams);
        if (pageInfo.getSize() > 0) {
            model.addAttribute("maxId", pageInfo.getList().get(0).getId());
            model.addAttribute("sinceId", pageInfo.getList().get(pageInfo.getSize() - 1).getId());
        } else {
            model.addAttribute("maxId", 0);
            model.addAttribute("sinceId", 0);
        }
        model.addAttribute("pageInfo", pageInfo);
        */

        return "article/index";
    }

    @GetMapping("/{id}")
    public String info(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        BlogArticle article = articleService.findOne(id);
        if(article == null || !article.getIsValid()){
            article = new BlogArticle();
        }
        model.addAttribute("article", article);

        return "article/article";

    }

    @GetMapping("editPage")
    public String editPage(Integer id, @CurrentUser BlogUser user, Model model, HttpServletRequest request) {

        model.addAttribute("articleTypes", ArticleTypeEnum.values());
        return "article/edit";
    }

    @PostMapping("save")
    @ResponseBody
    public SimpleResult save(BlogArticle article, String tagNames, @CurrentUser BlogUser user) {

        //todo 关键词提取

        List<String> memos = HanLP.extractSummary(article.getContent(), 1, ",");

        article.setMemo(memos.get(0));
        String str = new Gson().toJson(tagNames);
        System.out.println(str);

        return articleService.save(article, tagNames, user);
    }

}
