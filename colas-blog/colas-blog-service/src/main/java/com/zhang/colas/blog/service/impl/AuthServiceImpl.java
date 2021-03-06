package com.zhang.colas.blog.service.impl;

import com.zhang.colas.blog.api.service.AuthService;
import com.zhang.colas.blog.entity.BlogUser;
import com.zhang.colas.blog.mapper.BlogUserMapper;
import com.zhang.colas.common.SimpleResult;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author zxk
 * @date 2018-02-06 23:38:47
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final String CACHE_KEY = "'user'";
    private static final String USER_CACHE_NAME = "users";


    private PasswordService passwordService = new DefaultPasswordService();

    @Autowired
    private BlogUserMapper userMapper;

    @Transactional
    @Override
    public SimpleResult registerUser(BlogUser user) {

        BlogUser checkUser = getUserByUsername(user.getUsername());

        if (null != checkUser) {
            return SimpleResult.responseError("用户名已存在");
        }
        user.setPassword(passwordService.encryptPassword(user.getPassword()));

        userMapper.insertSelective(user);

        return SimpleResult.responseOk("");
    }

    @Cacheable(value = USER_CACHE_NAME, key = "'user_'+#username")
    @Override
    public BlogUser getUserByUsername(String username) {

        BlogUser user = new BlogUser();
        user.setUsername(username.trim());
        user.setIsValid(true);
        List<BlogUser> list = userMapper.selectListByModel(user);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
