package com.zhang.colas.blog.utils;

import com.zhang.colas.blog.entity.BlogUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroUserUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroUserUtils.class);

    public static BlogUser getSessionUser() {

        Subject subject = SecurityUtils.getSubject();
        Object o = SecurityUtils.getSubject().getPrincipal();

        LOGGER.trace("获取的信息", o);
        return null;
    }
}
