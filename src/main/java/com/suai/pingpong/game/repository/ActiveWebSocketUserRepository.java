package com.suai.pingpong.game.repository;

import com.suai.pingpong.game.model.ActiveWebSocketUser;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ActiveWebSocketUserRepository {
    @Getter
    private static final ActiveWebSocketUserRepository activeWebSocketUserRepository = new ActiveWebSocketUserRepository();
    @Getter
    private List<ActiveWebSocketUser> listActiveWebSocketUser = new ArrayList<>();

    public ActiveWebSocketUser findUserByUsername(String username){
        for(ActiveWebSocketUser user : listActiveWebSocketUser){
            if (user.getOwner().equals(username)){
                return user;
            }
        }
        return null;
    }

    public ActiveWebSocketUser findUserBySessionId(String sessionId){
        for(ActiveWebSocketUser user : listActiveWebSocketUser){
            if (user.getSessionId().equals(sessionId)){
                return user;
            }
        }
        return null;
    }

    public void setUser(ActiveWebSocketUser user){
        listActiveWebSocketUser.add(user);
    }

    public void removeUser(ActiveWebSocketUser user){
        listActiveWebSocketUser.remove(user);
    }
}
