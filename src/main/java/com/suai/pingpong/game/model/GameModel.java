package com.suai.pingpong.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class GameModel {
    @Getter
    @Setter
    private String act;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private int x;
    @Getter
    @Setter
    private int y;
}
