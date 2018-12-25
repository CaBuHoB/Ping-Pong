package com.suai.pingpong.game.controller;

import com.suai.pingpong.game.model.ActiveWebSocketUser;
import com.suai.pingpong.game.repository.ActiveWebSocketUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WebSocketEventListenerTest {

    @InjectMocks
    WebSocketEventListener webSocketEventListener;
    @Mock
    SimpMessagingTemplate simpMessagingTemplate;

    @Mock
    SessionConnectEvent connectEvent;

    @Mock
    SessionSubscribeEvent subscribeEvent;
    @Mock
    Message<byte[]> message;
    @Mock
    UsernamePasswordAuthenticationToken user;

    @Mock
    SessionDisconnectEvent disconnectEvent;

    @Test
    public void handleWebSocketConnectListener() {
        webSocketEventListener.handleWebSocketConnectListener(connectEvent);
    }

    @Test
    public void handleWebSocketSubscribeListener() {
        Map<String, Object> messages = new HashMap<>();
        messages.put("simpDestination", "/test");
        messages.put("simpUser", user);
        messages.put("simpSessionId", "someId");
        when(subscribeEvent.getMessage()).thenReturn(message);
        when(subscribeEvent.getMessage().getHeaders()).thenReturn(new MessageHeaders(messages));
        when(user.getName()).thenReturn("test");
        webSocketEventListener.handleWebSocketSubscribeListener(subscribeEvent);
        when(user.getName()).thenReturn("test2");
        webSocketEventListener.handleWebSocketSubscribeListener(subscribeEvent);
    }

    @Test
    public void handleWebSocketDisconnectListener() {
        WebSocketEventListener webSocketEventListener = new WebSocketEventListener(simpMessagingTemplate);
        ActiveWebSocketUserRepository repository = ActiveWebSocketUserRepository.getActiveWebSocketUserRepository();
        repository.setUser(new ActiveWebSocketUser("test", "test"));
        when(disconnectEvent.getSessionId()).thenReturn("test");
        webSocketEventListener.handleWebSocketDisconnectListener(disconnectEvent);
    }
}