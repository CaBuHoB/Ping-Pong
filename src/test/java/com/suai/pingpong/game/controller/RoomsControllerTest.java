package com.suai.pingpong.game.controller;

import com.suai.pingpong.game.model.GameModel;
import com.suai.pingpong.login.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RoomsControllerTest {

    @Mock
    private UserService userService;

    private final RoomsController roomsController = new RoomsController(userService);

    @Test
    public void sendCoordinate() {
        GameModel coordinates = new GameModel();
        GameModel coordinatesFromMethod = roomsController.sendCoordinate(coordinates);
        assertEquals(coordinates.getAct(), coordinatesFromMethod.getAct());
        assertEquals(coordinates.getUsername(), coordinatesFromMethod.getUsername());
        assertEquals(coordinates.getY(), coordinatesFromMethod.getY());
    }

    @Test
    public void sendCoordinate1() {
        roomsController.sendCoordinate(null);
    }
}