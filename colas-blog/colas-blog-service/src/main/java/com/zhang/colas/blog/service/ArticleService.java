package com.zhang.colas.blog.service;

import com.github.pagehelper.PageInfo;
import com.zhang.colas.blog.entity.BlogArticle;
import com.zhang.colas.common.SimpleResult;

/**
 * @author zxk
 * @date 2018-02-04 23:11:27
 */
public interface ArticleService {

    int deleteByPrimaryKey(Integer id);

    int insert(BlogArticle record);

    int insertSelective(BlogArticle record);

    BlogArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogArticle record);

    int updateByPrimaryKeyWithBLOBs(BlogArticle record);

    int updateByPrimaryKey(BlogArticle record);


    SimpleResult save(BlogArticle blogArticle);

    PageInfo<BlogArticle> selectArticleListPage(int pageNum, int pageSize);
}
