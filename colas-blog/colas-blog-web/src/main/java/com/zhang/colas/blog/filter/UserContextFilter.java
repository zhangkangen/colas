package com.zhang.colas.blog.filter;

import com.zhang.colas.blog.entity.BlogUser;
import com.zhang.colas.blog.service.AuthService;
import com.zhang.colas.blog.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


public class UserContextFilter extends PathMatchingFilter {

    @Autowired
    private AuthService authService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        System.out.println("扩展PathMatchingFilter");

        String username = (String) SecurityUtils.getSubject().getPrincipal();

        if(StringUtils.isNotBlank(username)) {
            BlogUser user = authService.getUserByUsername(username);
            if (null != user) {
                request.setAttribute(Constants.CURRENT_USER, user);
            }
        }
        return true;
    }
}
