package com.zhang.colas.blog.service;

import com.zhang.colas.blog.entity.BlogUser;
import com.zhang.colas.common.SimpleResult;

public interface AuthService {
    SimpleResult registerUser(BlogUser user);

    BlogUser getUserByUsername(String username);
}
