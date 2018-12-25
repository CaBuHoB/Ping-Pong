package com.suai.pingpong.game.model;

import org.junit.Test;

import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class ActiveWebSocketUserTest {

    @Test
    public void setUsername() {
        ActiveWebSocketUser activeWebSocketUser = new ActiveWebSocketUser("test", "test");
        activeWebSocketUser.setUsername("test2", "test2");
    }

    @Test
    public void getUsername() {
        ActiveWebSocketUser activeWebSocketUser = new ActiveWebSocketUser("test", "test");
        activeWebSocketUser.setUsername("test", "id");
        Map<String, String> username = activeWebSocketUser.getUsername();
        assertEquals("id", username.get("test"));
    }

    @Test
    public void setOwner() {
        ActiveWebSocketUser activeWebSocketUser = new ActiveWebSocketUser("test", "test");
        activeWebSocketUser.setOwner("test");
    }

    @Test
    public void setSessionId() {
        ActiveWebSocketUser activeWebSocketUser = new ActiveWebSocketUser("test", "test");
        activeWebSocketUser.setSessionId("test");
    }

    @Test
    public void equals() {
        ActiveWebSocketUser activeWebSocketUser = new ActiveWebSocketUser("test", "test");
        ActiveWebSocketUser activeWebSocketUser2 = new ActiveWebSocketUser("test", "test");
        ActiveWebSocketUser activeWebSocketUser3 = new ActiveWebSocketUser("test2", "test2");
        Random random = new Random();
        assertEquals(activeWebSocketUser, activeWebSocketUser);
        assertEquals(activeWebSocketUser, activeWebSocketUser2);
        assertNotEquals(activeWebSocketUser, activeWebSocketUser3);
        assertNotEquals(activeWebSocketUser, random);
    }

    @Test
    public void canEqual() {
        ActiveWebSocketUser activeWebSocketUser = new ActiveWebSocketUser("test", "test");
        ActiveWebSocketUser activeWebSocketUser2 = new ActiveWebSocketUser("test", "test");
        assertTrue(activeWebSocketUser.canEqual(activeWebSocketUser2));
    }

    @Test
    public void hashCodeTest() {
        ActiveWebSocketUser activeWebSocketUser = new ActiveWebSocketUser("test", "test");
        int i = activeWebSocketUser.hashCode();
        assertNotEquals(0, i);
    }

    @Test
    public void toStringTest() {
        ActiveWebSocketUser activeWebSocketUser = new ActiveWebSocketUser("test", "test");
        String s = activeWebSocketUser.toString();
        assertEquals("ActiveWebSocketUser(owner=test, sessionId=test, username={test=test})", s);
    }
}