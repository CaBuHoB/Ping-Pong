package com.suai.pingpong.game.repository;

import com.suai.pingpong.game.model.ActiveWebSocketUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ActiveWebSocketUserRepositoryTest {

    private final ActiveWebSocketUserRepository activeWebSocketUserRepository = new ActiveWebSocketUserRepository();

    @Test
    public void setUser() {
        ActiveWebSocketUser user = new ActiveWebSocketUser("test", "test");
        activeWebSocketUserRepository.setUser(user);
    }

    @Test
    public void findUserByUsername() {
        ActiveWebSocketUser user = new ActiveWebSocketUser("test", "test");
        activeWebSocketUserRepository.setUser(user);
        assertNotNull(activeWebSocketUserRepository.findUserByUsername("test"));
    }

    @Test
    public void findUserBySessionId() {
        ActiveWebSocketUser user = new ActiveWebSocketUser("test", "test");
        activeWebSocketUserRepository.setUser(user);
        assertNotNull(activeWebSocketUserRepository.findUserBySessionId("test"));
    }

    @Test
    public void removeUser() {
        ActiveWebSocketUser user = new ActiveWebSocketUser("test", "test");
        activeWebSocketUserRepository.removeUser(user);
    }
}