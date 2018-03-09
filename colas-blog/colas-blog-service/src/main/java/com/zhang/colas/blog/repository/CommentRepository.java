package com.zhang.colas.blog.repository;

import com.zhang.colas.blog.entity.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<BlogComment, Integer> {
}
