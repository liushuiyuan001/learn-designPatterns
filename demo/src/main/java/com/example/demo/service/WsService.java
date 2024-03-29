package com.example.demo.service;

import com.example.demo.websocket.WebSocketServer;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WsService {

    @Resource
    public WebSocketServer webSocketServer;

    @Async
    public void sendInfo(String message, Long logId) {
        MDC.put("LOG_ID", String.valueOf(logId));
        webSocketServer.sendInfo(message);
    }
}
