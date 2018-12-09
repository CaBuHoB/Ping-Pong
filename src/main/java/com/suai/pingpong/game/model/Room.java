package com.suai.pingpong.game.model;

@SuppressWarnings("ALL")
public class Room {
    private String owner;
    private int numberOfUsers;

    public Room() {
        numberOfUsers = 1;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }
}
