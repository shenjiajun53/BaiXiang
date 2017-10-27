package com.baixiang.controller;

import com.baixiang.model.response.BaseBean;
import com.baixiang.model.response.RedirectBean;
import com.baixiang.model.response.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shenjiajun on 2017/10/27.
 */

@RestController
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Response greeting(@RequestParam(value = "message") String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Response<RedirectBean>(new RedirectBean(message), null);
    }
}
