package com.suai.pingpong.game.controller;

import com.suai.pingpong.game.model.GameModel;
import com.suai.pingpong.login.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
public class RoomsControllerTest {

    private final RoomsController roomsController = new RoomsController(new UserService(null, null, null));

    @Test
    public void view() {
        ModelAndView modelAndView = new ModelAndView("rooms.html");
        ModelAndView modelAndViewFromMethod = roomsController.view();
        assertEquals(modelAndView.getViewName(), modelAndViewFromMethod.getViewName());
        assertNotNull(modelAndViewFromMethod.getModel().get("rooms"));
    }

    @Test
    public void sendCoordinate() {
        GameModel coordinates = new GameModel();
        GameModel coordinatesFromMethod = roomsController.sendCoordinate(coordinates);
        assertEquals(coordinates.getAct(), coordinatesFromMethod.getAct());
        assertEquals(coordinates.getUsername(), coordinatesFromMethod.getUsername());
        assertEquals(coordinates.getY(), coordinatesFromMethod.getY());
    }
}