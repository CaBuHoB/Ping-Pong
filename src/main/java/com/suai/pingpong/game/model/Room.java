package com.suai.pingpong.game.model;

import lombok.Data;

@Data
public class Room {
    private String owner;
    private int numberOfUsers = 1;
}
