package com.suai.pingpong.login.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class RoleTest {

    @Test
    public void setId() {
        Role role = new Role();
        role.setId(1);
        assertEquals(1, role.getId());
    }

    @Test
    public void setRole() {
        Role role = new Role();
        role.setRole("test");
        assertEquals("test", role.getRole());
    }

    @Test
    public void equals() {
        Role role = new Role();
        Role role2 = new Role();
        role.setRole("test");
        assertNotEquals(role, role2);
        role2.setRole("test");
        assertEquals(role, role2);
        Random random = new Random();
        assertNotEquals(role, random);
    }

    @Test
    public void canEqual() {
        Role role = new Role();
        Role role2 = new Role();
        assertTrue(role.canEqual(role2));
    }

    @Test
    public void toStringTest() {
        Role role = new Role();
        String s = role.toString();
        assertEquals("Role(id=0, role=null)", s);
    }
}