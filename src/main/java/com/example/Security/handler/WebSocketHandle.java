package com.example.Security.handler;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class WebSocketHandle extends TextWebSocketHandler {
    private final List<WebSocketSession> webSocketSessions = new ArrayList<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session)throws Exception{
        webSocketSessions.add(session);
    }


}
