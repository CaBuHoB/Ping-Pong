package com.suai.pingpong.game.model;

import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

@Data
public class ActiveWebSocketUser {
    private String owner;
    private String sessionId;
    private final Map<String, String> username;

    public ActiveWebSocketUser(String owner, String sessionId) {
        this.sessionId = sessionId;
        this.owner = owner;
        this.username = new TreeMap<>();
        this.username.put(owner, sessionId);
    }

    public void setUsername(String username, String sessionId) {
        this.username.put(username, sessionId);
    }
}
