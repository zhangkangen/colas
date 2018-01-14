package com.zhang.colas.sns.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

/**
 * 静态文件路径
 * @author zxk
 * @date 2018-01-14 15:26:10
 */
@ControllerAdvice
public class ResourceUrlProviderController {


    @Autowired
    private ResourceUrlProvider resourceUrlProvider;

    @ModelAttribute("urls")
    public ResourceUrlProvider urls(){
        return this.resourceUrlProvider;
    }
}
