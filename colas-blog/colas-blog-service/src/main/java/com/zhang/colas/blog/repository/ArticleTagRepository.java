package com.zhang.colas.blog.repository;

import com.zhang.colas.blog.entity.BlogArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleTagRepository extends JpaRepository<BlogArticleTag, Integer> {
}
