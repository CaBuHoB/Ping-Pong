package com.suai.pingpong.game.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Room {
    @Getter
    @Setter
    private String owner;
    @Getter
    @Setter
    private int numberOfUsers = 1;
}
