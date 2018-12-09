package com.suai.pingpong.game.model;

@SuppressWarnings("ALL")
public class GameModel {
    private String act;
    private String username;
    private int x;
    private int y;

    public GameModel(String act, String username, int x, int y) {
        this.act = act;
        this.username = username;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }
}
