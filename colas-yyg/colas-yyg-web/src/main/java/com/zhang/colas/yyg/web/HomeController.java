package com.zhang.colas.yyg.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zxk
 * @date 2018-02-04 13:27:03
 */
@Controller
public class HomeController {

    @GetMapping({"index", ""})
    @ResponseBody
    public String index() {
        return "index";
    }
}
