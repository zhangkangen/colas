package com.zhang.colas.sns.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {


    @MessageMapping("/say")
    @SendTo("/topic/getResponse")
    public Object say(){
        return "ok";
    }
}
