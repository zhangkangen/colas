package com.zhang.colas.sns.web;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zhang.colas.common.utils.HttpClientUtils;
import com.zhang.colas.common.utils.key.SnowflakeIdWorker;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author zxk
 * @date 2018-01-14 23:24:17
 */
@Controller
public class IndexController {

    @Autowired
    private DefaultKaptcha captchaProducer;

    @RequestMapping({"", "index"})
    public String index() {

        return "/index";
    }
}
