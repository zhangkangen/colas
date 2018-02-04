package com.zhang.colas.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zxk
 * @date 2018-02-04 15:57:15
 */
@Controller
public class HomeController {

    @GetMapping({"","index"})
    public String index(){
        return "index";
    }
}
