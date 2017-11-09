package com.baixiang.service;

import com.baixiang.utils.logger.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendMessage(String message) {
        LogUtil.info(WebSocketService.class, message);
        simpMessagingTemplate.convertAndSend("/toApp/message", message);
    }
}
