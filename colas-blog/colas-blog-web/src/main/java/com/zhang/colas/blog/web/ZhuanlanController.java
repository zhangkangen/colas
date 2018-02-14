package com.zhang.colas.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"zhuanlan"})
public class ZhuanlanController {

    @GetMapping({"index", ""})
    public String index() {
        return "zhuanlan/index";
    }
}
