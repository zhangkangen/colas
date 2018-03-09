package com.zhang.colas.blog.repository;

import com.zhang.colas.blog.entity.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<BlogTag, Integer> {
}
