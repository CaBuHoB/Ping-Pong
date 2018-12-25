package com.suai.pingpong.game.repository;

import com.suai.pingpong.game.model.ActiveWebSocketUser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ActiveWebSocketUserRepositoryTest {

    private final ActiveWebSocketUserRepository activeWebSocketUserRepository = new ActiveWebSocketUserRepository();

    @Test
    public void getActiveWebSocketUserRepository() {
        List<ActiveWebSocketUser> listActiveWebSocketUser = activeWebSocketUserRepository.getListActiveWebSocketUser();
        Assert.assertEquals(0, listActiveWebSocketUser.size());
    }

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
        assertNull(activeWebSocketUserRepository.findUserByUsername("test2"));
    }

    @Test
    public void findUserBySessionId() {
        ActiveWebSocketUser user = new ActiveWebSocketUser("test", "test");
        activeWebSocketUserRepository.setUser(user);
        assertNotNull(activeWebSocketUserRepository.findUserBySessionId("test"));
        assertNull(activeWebSocketUserRepository.findUserBySessionId("test2"));
    }

    @Test
    public void removeUser() {
        ActiveWebSocketUser user = new ActiveWebSocketUser("test", "test");
        activeWebSocketUserRepository.removeUser(user);
    }
}