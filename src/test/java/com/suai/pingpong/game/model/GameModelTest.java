package com.suai.pingpong.game.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class GameModelTest {

    @Test
    public void getAct() {
        GameModel gameModel = new GameModel();
        assertNull(gameModel.getAct());
    }

    @Test
    public void getUsername() {
        GameModel gameModel = new GameModel();
        assertNull(gameModel.getUsername());
    }

    @Test
    public void isCanStart() {
        GameModel gameModel = new GameModel();
        gameModel.setCanStart(true);
        assertTrue(gameModel.isCanStart());
    }

    @Test
    public void getY() {
        GameModel gameModel = new GameModel();
        assertEquals(0, gameModel.getY());
    }

    private void ballAndScoresGetSetTest(GameModel.BallAndScores ballAndScores){
        ballAndScores.setOpponentScores(1);
        assertEquals(1, ballAndScores.getOpponentScores());
        ballAndScores.setPlayerScores(1);
        assertEquals(1, ballAndScores.getPlayerScores());
        ballAndScores.setX(1);
        assertEquals(1, ballAndScores.getX());
        ballAndScores.setY(1);
        assertEquals(1, ballAndScores.getY());
    }

    private void ballAndScoresOtherTest(GameModel.BallAndScores ballAndScores){
        assertNotEquals(0, ballAndScores.hashCode());
        assertEquals(ballAndScores, ballAndScores);
        assertTrue(ballAndScores.canEqual(ballAndScores));
        GameModel.BallAndScores ballAndScores2 = new GameModel.BallAndScores();
        assertNotEquals(ballAndScores, ballAndScores2);
        ballAndScoresGetSetTest(ballAndScores2);
        assertEquals(ballAndScores, ballAndScores2);
        assertNotEquals(ballAndScores, new Random());
        assertEquals("GameModel.BallAndScores(opponentScores=1, playerScores=1, x=1, y=1)",
                ballAndScores.toString());
    }

    @Test
    public void getBallAndScores() {
        GameModel gameModel = new GameModel();
        gameModel.setBallAndScores(new GameModel.BallAndScores());
        GameModel.BallAndScores ballAndScores = gameModel.getBallAndScores();
        ballAndScoresGetSetTest(ballAndScores);
        ballAndScoresOtherTest(ballAndScores);
    }

    @Test
    public void setAct() {
        GameModel gameModel = new GameModel();
        assertNull(gameModel.getAct());
    }

    @Test
    public void setUsername() {
        GameModel gameModel = new GameModel();
        gameModel.setUsername("test");
    }

    @Test
    public void setY() {
        GameModel gameModel = new GameModel();
        gameModel.setY(1);
    }

    @Test
    public void equals() {
        GameModel gameModel = new GameModel();
        assertEquals(gameModel, gameModel);
        GameModel gameModel2 = new GameModel();
        assertEquals(gameModel, gameModel2);
        gameModel2.setY(1);
        assertNotEquals(gameModel, gameModel2);
        assertNotEquals(gameModel, new Random());
    }

    @Test
    public void canEqual() {
        GameModel gameModel = new GameModel();
        gameModel.canEqual(gameModel);
    }

    @Test
    public void hashCodeTest() {
        GameModel gameModel = new GameModel();
        assertNotEquals(0, gameModel.hashCode());
    }

    @Test
    public void toStringTest() {
        GameModel gameModel = new GameModel();
        assertEquals("GameModel(act=null, username=null, canStart=false, y=0, ballAndScores=null)",
                gameModel.toString());
    }
}