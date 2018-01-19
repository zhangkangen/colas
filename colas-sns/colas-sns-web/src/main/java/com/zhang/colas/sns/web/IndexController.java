package com.zhang.colas.sns.web;

import com.zhang.colas.common.utils.key.SnowflakeIdWorker;
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


    SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0,0);

    @RequestMapping({"", "index"})
    public String index() {

        System.out.println(LocalDate.now().toString());
        System.out.println(FilenameUtils.getExtension("asdlfjasdlfa.jpg"));
        long id = idWorker.nextId();

        System.out.println(id);
        return "/index";
    }
}
