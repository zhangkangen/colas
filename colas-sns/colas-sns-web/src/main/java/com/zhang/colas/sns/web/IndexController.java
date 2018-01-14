package com.zhang.colas.sns.web;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

/**
 * @author zxk
 * @date 2018-01-14 23:24:17
 */
@Controller
public class IndexController {

    @RequestMapping({"", "index"})
    public String index() {

        System.out.println(LocalDate.now().toString());
        System.out.println(FilenameUtils.getExtension("asdlfjasdlfa.jpg"));

        return "/index";
    }
}
