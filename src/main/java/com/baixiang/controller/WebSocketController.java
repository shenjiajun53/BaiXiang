package com.baixiang.controller;

import com.baixiang.model.response.BaseBean;
import com.baixiang.model.response.RedirectBean;
import com.baixiang.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by shenjiajun on 2017/10/27.
 */

@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/toApp/message")
    public String greeting(@RequestParam(value = "message") String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return "get "+message;
    }
}
