package com.suai.pingpong.game.model;

import lombok.Data;

@Data
public class GameModel {
    private String act;
    private String username;
    private boolean canStart;
    private int y;
    private BallAndScores ballAndScores;

    @Data
    static class BallAndScores {
        private int opponentScores;
        private int playerScores;
        private int x;
        private int y;
    }
}
