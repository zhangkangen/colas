package com.zhang.colas.blog.web;

import com.zhang.colas.blog.api.service.AuthService;
import com.zhang.colas.blog.entity.BlogUser;
import com.zhang.colas.common.SimpleResult;
import com.zhang.colas.common.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author zxk
 * @date 2018-02-04 15:57:15
 */
@Controller
public class HomeController extends BaseController {

    @Autowired
    private AuthService authService;

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
        SecurityUtils.getSubject().logout();
        return "forgot";
    }

    @GetMapping("empty")
    public String empty() {
        return "empty";
    }

    @PostMapping("login")
    public String doLogin(BlogUser user, Model model) {

        LOGGER.info(user.getUsername());

        try {
            AuthenticationToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException e) {
            model.addAttribute("error", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("error", "密码错误");
            return "login";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            model.addAttribute("error", "未知异常");
            return "login";
        }
        return "redirect:index";
    }

    @PostMapping("register")
    public String doRegister(BlogUser user, Model model) {

        //todo 判断用户名是否存在
        String username = user.getUsername();
        String password = user.getPassword();
        SimpleResult result = authService.registerUser(user);
        if (result.getSuccess()) {

            try {
                AuthenticationToken token = new UsernamePasswordToken(username, password);
                SecurityUtils.getSubject().login(token);
                return "redirect:index";
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        model.addAttribute("error", result.getMsg());
        return "register";
    }
}
