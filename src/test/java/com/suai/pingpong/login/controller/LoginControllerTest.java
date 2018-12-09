package com.suai.pingpong.login.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginControllerTest {

    private final LoginController loginController = new LoginController();

    @Test
    public void login() {
        assertEquals("login.html", loginController.login().getViewName());
    }

    @Test
    public void registration() {
        ModelAndView modelAndView = loginController.registration();
        assertEquals("registration.html", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel().get("user"));
    }

}