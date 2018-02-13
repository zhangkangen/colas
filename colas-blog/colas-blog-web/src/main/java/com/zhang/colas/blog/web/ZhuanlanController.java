package com.zhang.colas.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("zhuanlan")
public class ZhuanlanController {

    public String index(){
        return "zhuanlan";
    }
}
