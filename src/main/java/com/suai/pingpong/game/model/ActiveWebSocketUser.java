package com.suai.pingpong.game.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;

public class ActiveWebSocketUser {
    @Getter
    @Setter
    @NonNull
    private String owner;
    @Setter
    @Getter
    @NonNull
    private String sessionId;
    @Getter
    private Map<String, String> username;

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
