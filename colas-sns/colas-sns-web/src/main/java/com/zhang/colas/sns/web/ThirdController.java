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
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Value("${weibo.client.id}")
    private String weiboClientId;
    @Value("${weibo.client.secret}")
    private String weiboClientSecret;
    @Value("${weibo.client.redirect.uri}")
    private String weiboRedirectUri;

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
        System.out.println(code);

        String url =
                String.format("https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s&redirect_uri=%s", githubClientId, githubClientSecret, code, githubRedirectUri);
        String result = HttpClientUtils.doSimpleGet(url);
        LOGGER.info(result);

        String access_token = StringUtils.split(result, "&")[0];
        access_token = StringUtils.split(access_token,"=")[1];
        LOGGER.info(access_token);
        String getUserUrl = String.format("https://api.github.com/user?access_token=%s", access_token);

        String userInfo = HttpClientUtils.doSimpleGet(getUserUrl);

        GithubUser githubUser = new Gson().fromJson(userInfo, GithubUser.class);

        SUser user = authService.getSUserByGithubId(githubUser.getId());
        if (user == null) {
            user = authService.createUser(githubUser);
        }

        request.getSession().setAttribute(Constants.SESSION_USER, user);
        return "redirect:/mobile/index";
    }

    @RequestMapping("weibo/login")
    public String weiboLogin() {

        String url = "http://q.51encounter.com/weibo/connect";
        String redirectUrl = String.format("https://api.weibo.com/oauth2/authorize?client_id=%s&response_type=code&redirect_uri=%s", weiboClientId, url);
        return "redirect:" + redirectUrl;
    }

    @RequestMapping("weibo/connect")
    @ResponseBody
    public String weiboConnect(String code) {

        //https://api.weibo.com/oauth2/access_token?client_id=YOUR_CLIENT_ID&client_secret=YOUR_CLIENT_SECRET&grant_type=authorization_code&redirect_uri=YOUR_REGISTERED_REDIRECT_URI&code=CODE
        String result = HttpClientUtils.doSimplePost(String.format("https://api.weibo.com/oauth2/access_token?client_id=%s&client_secret=%s&grant_type=authorization_code&redirect_uri=%s&code=%s", weiboClientId, weiboClientSecret, weiboRedirectUri, code), null);

        LOGGER.info(result);
        return code;
    }

    @RequestMapping("weibo/getInfo")
    @ResponseBody
    public String getWeiboInfo() {
        String access_token = "2.00um5IuB058hQzbb8f2d84e9bhXynC";
        String getInfoUrl = HttpClientUtils.doSimplePost(String.format("https://api.weibo.com/oauth2/get_token_info?access_token=%s", access_token), null);
        String uid = "1745609560";
        String url = String.format("https://api.weibo.com/2/users/show.json?access_token=%s&uid=%s", access_token, uid);
        String result = HttpClientUtils.doSimpleGet(url);
        LOGGER.info(result);
        return result;
    }

}
