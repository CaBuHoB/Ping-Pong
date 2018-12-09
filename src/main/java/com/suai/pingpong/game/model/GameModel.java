package com.suai.pingpong.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GameModel {
    private String act;
    private String username;
    private int x;
    private int y;
}
