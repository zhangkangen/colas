package com.zhang.colas.blog.web;

import com.zhang.colas.blog.entity.BlogUser;
import com.zhang.colas.common.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zxk
 * @date 2018-02-04 15:57:15
 */
@Controller
public class HomeController extends BaseController {

    @GetMapping({"", "index"})
    public String index() {
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @GetMapping("forgot")
    public String forgot() {
        return "forgot";
    }

    @GetMapping("empty")
    public String empty() {
        return "empty";
    }

    @PostMapping("login")
    public String doLogin(BlogUser user) {

        LOGGER.info(user.getUsername());

        AuthenticationToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        SecurityUtils.getSubject().login(token);
        return "login";
    }
}
