package com.zhang.colas.sns.web;

import com.zhang.colas.sns.entity.SUser;
import com.zhang.colas.sns.utils.Constants;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    public SUser getSessionUser(HttpServletRequest request){
        return (SUser) request.getSession().getAttribute(Constants.SESSION_USER);
    }
}
