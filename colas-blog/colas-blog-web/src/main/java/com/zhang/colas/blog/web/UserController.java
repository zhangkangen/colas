package com.zhang.colas.blog.web;

import com.zhang.colas.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zxk
 * @date 2018-02-07 15:44:27
 */
@Controller
@RequestMapping("user")
public class UserController  extends BaseController {

    @GetMapping("")
    public String myProfile(){
        return "user/profile";
    }
}
