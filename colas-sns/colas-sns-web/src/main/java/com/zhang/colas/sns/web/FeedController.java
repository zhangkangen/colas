package com.zhang.colas.sns.web;

import com.zhang.colas.common.PageParams;
import com.zhang.colas.common.PageResult;
import com.zhang.colas.sns.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zxk
 * @date 2018-01-20 01:18:28
 */
@Controller
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public PageResult list(PageParams pageParams){

        PageResult result = feedService.queryList(pageParams);
        return result;
    }
}
