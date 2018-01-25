package com.zhang.colas.sns.web;

import com.zhang.colas.sns.entity.vo.WebSocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {


    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/say")
    @SendTo("/topic/getResponse")
    public Object say(WebSocketMessage message){
        
        System.out.println(message);
        return "ok";
    }
}
