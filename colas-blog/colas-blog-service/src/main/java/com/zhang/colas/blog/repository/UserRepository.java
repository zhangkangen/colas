package com.zhang.colas.blog.repository;

import com.zhang.colas.blog.entity.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<BlogUser, Integer> {
}
