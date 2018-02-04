package com.zhang.colas.blog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Utf8EncodingFilter extends GenericFilterBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utf8EncodingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Utf8EncodingRequest req = new Utf8EncodingRequest((HttpServletRequest) request);
        chain.doFilter(req, response);
    }

    @Override
    protected void initFilterBean() throws ServletException {
        LOGGER.info("---------------------加载Utf8EncodingFilter----------------------");
        super.initFilterBean();
    }
}
