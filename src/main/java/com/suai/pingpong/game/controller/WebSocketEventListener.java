package com.suai.pingpong.game.controller;

import com.alibaba.fastjson.JSON;
import com.suai.pingpong.game.model.ActiveWebSocketUser;
import com.suai.pingpong.game.model.GameModel;
import com.suai.pingpong.game.model.ModelRoom;
import com.suai.pingpong.game.repository.ActiveWebSocketUserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;


@Component
@Log4j2
public class WebSocketEventListener {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ActiveWebSocketUserRepository repository = ActiveWebSocketUserRepository.getActiveWebSocketUserRepository();

    @Autowired
    public WebSocketEventListener(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        log.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketSubscribeListener(SessionSubscribeEvent event) {
        MessageHeaders headers = event.getMessage().getHeaders();
        String owner = (String) headers.get("simpDestination");
        assert owner != null;
        owner = owner.substring(owner.lastIndexOf('/') + 1);
        UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) headers.get("simpUser");
        assert user != null;
        String username = user.getName();
        String id = (String) headers.get("simpSessionId");

        if (username.equals(owner)) {
            ActiveWebSocketUser activeWebSocketUser = new ActiveWebSocketUser(username, id);
            repository.setUser(activeWebSocketUser);
        } else {
            ActiveWebSocketUser activeWebSocketUser = repository.findUserByUsername(owner);
            activeWebSocketUser.setUsername(username, id);
        }

        log.info("Received a new web socket subscribe");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        String id = event.getSessionId();
        ActiveWebSocketUser activeWebSocketUser = repository.findUserBySessionId(id);
        String username = activeWebSocketUser.getOwner();
        GameModel gameModel = new GameModel();
        gameModel.setAct("close");
        gameModel.setUsername(username);
        String jsonString = JSON.toJSONString(gameModel);
        simpMessagingTemplate.convertAndSend("/topic/roomSocket/" + username, jsonString);
        repository.removeUser(activeWebSocketUser);
        ModelRoom.getInstance().deleteRoom(username);
    }
}