package com.zhang.colas.sns.web.mobile;

import com.zhang.colas.sns.entity.SUser;
import com.zhang.colas.sns.service.AuthService;
import com.zhang.colas.sns.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zxk
 * @date 2018-01-28 15:33:14
 */
@Controller
@RequestMapping("/mobile")
public class HomeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){

        SUser user = getSessionUser(request);

        return "mobile/index";
    }
}
