package com.zhang.colas.blog.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zhang.colas.blog.filter.UserContextFilter;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {



    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {


        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);


        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();



        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/hplus4.1/**", "anon");
        filterChainDefinitionMap.put("/inspinia/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");

        filterChainDefinitionMap.put("/index*/**", "anon");
        filterChainDefinitionMap.put("/", "anon");

        //登陆注册相关
        filterChainDefinitionMap.put("/register*/**", "anon");
        filterChainDefinitionMap.put("/forgot*/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");

        filterChainDefinitionMap.put("/article/editPage*", "authc");

        //其他页面
        filterChainDefinitionMap.put("/article*/**", "anon");


        filterChainDefinitionMap.put("/**", "authc,userContext");

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        Map filterMap = shiroFilterFactoryBean.getFilters();
        filterMap.put("userContext", userContextFilter());

        shiroFilterFactoryBean.setFilters(filterMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());

        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(new PasswordMatcher());
        return myShiroRealm;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public UserContextFilter userContextFilter(){
        return new UserContextFilter();
    }
}
