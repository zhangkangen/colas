package com.zhang.colas.blog.repository;

import com.zhang.colas.blog.entity.BlogArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @author zxk
 * @date 2018-03-11 13:07:22
 */
public interface ArticleRepository extends JpaRepository<BlogArticle, Integer> {


    /**
     * 查询要发布的文章
     * @param queryArticle
     * @return
     */
    @Query("select t from BlogArticle t where t.articleStatus = 2 and t.isValid = 1 and t.publishTime <= :#{#queryArticle.publishTime}")
    List<BlogArticle> queryShallPublishArticleList(@Param("queryArticle") BlogArticle queryArticle);
}
