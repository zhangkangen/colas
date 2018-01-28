package com.zhang.colas.sns.web;

import com.github.pagehelper.Constant;
import com.google.gson.Gson;
import com.zhang.colas.common.utils.HttpClientUtils;
import com.zhang.colas.common.vo.GithubUser;
import com.zhang.colas.sns.entity.SUser;
import com.zhang.colas.sns.service.AuthService;
import com.zhang.colas.sns.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * 第三方登录
 *
 * @author zxk
 * @date 2018-01-28 11:53:16
 */
@Controller
public class ThirdController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdController.class);

    @Value("${github_client_id}")
    private String githubClientId;
    @Value("${github.client.secret}")
    private String githubClientSecret;
    @Value("${github.authorize.url}")
    private String githubAuthorizeUrl;
    @Value("${github.authorize.scope}")
    private String githubAuthorizeScope;
    @Value("${github.redirect_uri}")
    private String githubRedirectUri;

    @Autowired
    private AuthService authService;


    @Autowired
    private HttpServletRequest request;

    @RequestMapping("github/login")
    public String githubLogin() {
        String state = String.valueOf(new Random().nextLong());
        String url = String.format("redirect:%s?client_id=%s&state=%s&redirect_uri=%s", githubAuthorizeUrl, githubClientId, state, githubRedirectUri);
        return url;
    }

    @RequestMapping("github/connect")
    public String githubConnect(String code, String state) {
        //todo 获取access_token
        System.out.println(code);

        String url =
                String.format("https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s&redirect_uri=%s", githubClientId, githubClientSecret, code, githubRedirectUri);
        String result = HttpClientUtils.doSimpleGet(url);
        LOGGER.info(result);

        String access_token = StringUtils.split(result, ",")[0];

        LOGGER.info(access_token);
        String getUserUrl = String.format("https://api.github.com/user?%s", access_token);

        String userInfo = HttpClientUtils.doSimpleGet(getUserUrl);

        GithubUser githubUser = new Gson().fromJson(userInfo, GithubUser.class);

        SUser user = authService.getSUserByGithubId(githubUser.getId());
        if (user == null) {
            user = authService.createUser(githubUser);
        }

        request.getSession().setAttribute(Constants.SESSION_USER,user);
        return "redirect:/mobile/index";
    }


}
