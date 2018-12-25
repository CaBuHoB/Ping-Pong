package com.suai.pingpong.login.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PingPongApplication.class)
@AutoConfigureMockMvc
public class LoginControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void login() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mvc.perform(post("/registration").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("name=test&lastName=test&username=login&email=login@test.ru&password=testpas").session(session));
        mvc.perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("username=" + "login" + "&password=testpas").session(session));

        mvc.perform(get("/login").session(session)).andExpect(status().isOk());
        mvc.perform(get("/profile").session(session)).andExpect(redirectedUrl(null));
    }

    @Test
    public void registration() throws Exception {
        mvc.perform(get("/registration")).andExpect(status().isOk());
    }

    @Test
    public void createNewUser() throws Exception {
        mvc.perform(post("/registration").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("name=test&lastName=test&username=createNewUser&email=createNewUser@test.ru&password=testpas"))
                .andExpect(model().attribute("successMessage", "User has been registered successfully"));
        mvc.perform(post("/registration").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("name=test&lastName=test&username=createNewUser&email=createNewUser@test.ru&password=testpas"))
                .andExpect(model().attributeHasErrors("user"));
        mvc.perform(post("/registration").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("name=test&lastName=test&username=createNewUser&email=test2@test.ru&password=testpas"))
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void profile() throws Exception {
        mvc.perform(get("/profile")).andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void index() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mvc.perform(get("/").session(session));
        mvc.perform(post("/registration").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("name=test&lastName=test&username=login&email=login@test.ru&password=testpas").session(session));
        mvc.perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("username=" + "login" + "&password=testpas").session(session));
        mvc.perform(get("/").session(session));
    }
}