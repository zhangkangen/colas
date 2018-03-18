package com.zhang.colas.blog.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhang.colas.blog.entity.BlogUser;
import com.zhang.colas.blog.service.AuthService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyShiroRealm.class);

    @Reference(version = "1.0.0",
    application = "${dubbo.application.id}",
    url = "dubbo://localhost:12345")
    private AuthService authService;

    private PasswordService passwordService = new DefaultPasswordService();

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        LOGGER.info("==== 权限校验 ====");
        return null;
    }

    /**
     * 身份认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        LOGGER.info("do myShiroRealm.AuthenticationInfo");

        try {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

            String username = token.getUsername();

            BlogUser user = authService.getUserByUsername(username);

            if (user != null) {
                return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
