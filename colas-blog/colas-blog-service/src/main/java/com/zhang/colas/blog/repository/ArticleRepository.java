package com.zhang.colas.blog.repository;

import com.zhang.colas.blog.entity.BlogArticle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<BlogArticle, Integer> {
}
