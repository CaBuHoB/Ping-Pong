package com.suai.pingpong.login.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void setId() {
        User user = new User();
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    public void equals() {
        User user = new User();
        User user2 = new User();
        assertEquals(user, user);
        assertEquals(user, user2);
        user2.setId(1);
        assertNotEquals(user, user2);
        Random random = new Random();
        assertNotEquals(user, random);
    }

    @Test
    public void hashCodeTest() {
        User user = new User();
        int i = user.hashCode();
        assertNotEquals(0, i);
    }
}