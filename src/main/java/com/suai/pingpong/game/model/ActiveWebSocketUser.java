package com.suai.pingpong.game.model;

import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("unused")
public class ActiveWebSocketUser {

    private String owner;
    private String sessionId;
    @SuppressWarnings("CanBeFinal")
    private Map<String, String> username;

    public  ActiveWebSocketUser(String owner, String sessionId) {
        this.sessionId = sessionId;
        this.owner = owner;
        this.username = new TreeMap<>();
        this.username.put(owner, sessionId);
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Map<String, String>getUsername() {
        return username;
    }

    public void setUsername(String username, String sessionId) {
        this.username.put(username, sessionId);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
