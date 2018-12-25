package com.suai.pingpong.game.controller;

import com.suai.pingpong.PingPongApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PingPongApplication.class)
@AutoConfigureMockMvc
public class RoomsControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private MockHttpSession logIn(String username) throws Exception {
        MockHttpSession session = new MockHttpSession();
        mvc.perform(post("/registration").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("name=test&lastName=test&username=" + username +
                        "&email=" + username + "@test.ru&password=testpas").session(session));
        mvc.perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("username=" + username + "&password=testpas").session(session));
        return session;
    }

    @Test
    public void view() throws Exception {
        MockHttpSession session = logIn("view");
        mvc.perform(get("/rooms").session(session)).andDo(print()).
                andExpect(redirectedUrl(null));
    }

    @Test
    public void getRooms() throws Exception {
        MockHttpSession session = logIn("getRooms");
        mvc.perform(get("/rooms.list").session(session)).andDo(print());
    }

    @Test
    public void create() throws Exception {
        MockHttpSession session = logIn("create");
        mvc.perform(post("/rooms").session(session)).andDo(print())
                .andExpect(redirectedUrl("/rooms/create"));
    }

    @Test
    public void openRoom() throws Exception {
        MockHttpSession session = logIn("openRoom");
        mvc.perform(post("/rooms").session(session)).andDo(print())
                .andExpect(redirectedUrl("/rooms/openRoom"));
        mvc.perform(get("/rooms/someRoom").session(session))
                .andExpect(MockMvcResultMatchers.view().name("wrongRoomName.html"));
        mvc.perform(get("/rooms/openRoom").session(session)).andDo(print());

        MockHttpSession session2 = logIn("openRoom2");
        mvc.perform(get("/rooms/openRoom").session(session2)).andDo(print());
    }
}