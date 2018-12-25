package com.suai.pingpong.game.model;

import org.junit.Test;

public class ModelRoomTest {

    @Test
    public void deleteRoom() {
        ModelRoom instance = ModelRoom.getInstance();
        Room room = new Room();
        room.setOwner("test");
        instance.add(room);
        instance.deleteRoom("test");
        instance.deleteRoom("test2");
    }

    @Test
    public void setNumberOfUsers() {
        ModelRoom instance = ModelRoom.getInstance();
        Room room = new Room();
        room.setOwner("test");
        instance.add(room);
        ModelRoom.setNumberOfUsers("test", 2);
    }
}