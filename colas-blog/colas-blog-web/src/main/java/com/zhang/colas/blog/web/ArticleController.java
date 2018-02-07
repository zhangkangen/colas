package com.zhang.colas.blog.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.hankcs.hanlp.HanLP;
import com.zhang.colas.blog.entity.BlogArticle;
import com.zhang.colas.blog.enums.ArticleTypeEnum;
import com.zhang.colas.blog.service.ArticleService;
import com.zhang.colas.common.SimpleResult;
import com.zhang.colas.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String index(@RequestParam(value = "pageNum", required = true, defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize, Model model) {

        PageInfo<BlogArticle> pageInfo = articleService.selectArticleListPage(pageNum, pageSize);

        model.addAttribute("pageInfo", pageInfo);

        return "article/index";
    }

    @GetMapping("/{id}")
    public String info(@PathVariable("id") Integer id, Model model) {

        BlogArticle article = articleService.selectByPrimaryKey(id);
        model.addAttribute("article", article);
        return "article/article";

    }

    @GetMapping("editPage")
    public String editPage(Integer id, Model model) {
        model.addAttribute("articleTypes", ArticleTypeEnum.values());
        return "article/edit";
    }

    @PostMapping("save")
    @ResponseBody
    public SimpleResult save(BlogArticle blogArticle) {


        //todo 关键词提取
        //todo 文章摘要生成
        List<String> memos = HanLP.extractSummary(blogArticle.getContent(),1,",");

        return SimpleResult.responseOk("ok");
        //return articleService.save(blogArticle);
    }

}
