package com.suai.pingpong.login.controller;

import com.suai.pingpong.login.model.ModelQuotes;
import com.suai.pingpong.login.model.Quote;
import com.suai.pingpong.login.model.User;
import com.suai.pingpong.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
public class LoginController {

    private static final String URL_HTML = "registration.html";
    private static final String USERNAME = "username";

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }


    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName(URL_HTML);
        return modelAndView;
    }

    private void checkErrors(@Valid User user, BindingResult bindingResult) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue(USERNAME, "error.user",
                            "There is already a user registered with the username provided");
        }
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        checkErrors(user, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(URL_HTML);
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName(URL_HTML);
        }
        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        modelAndView.addObject("name", user.getName());
        modelAndView.addObject("userName", user.getUsername());
        modelAndView.addObject("lastName", user.getLastName());
        modelAndView.addObject("email", user.getEmail());
        modelAndView.setViewName("profile.html");
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index.html");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        if (user == null) {
            modelAndView.addObject(USERNAME, "guest");
        } else {
            modelAndView.addObject(USERNAME, user.getUsername());
        }
        Quote quote = new ModelQuotes().getRandomQuote();
        modelAndView.addObject("quote", quote);
        return modelAndView;
    }
}
