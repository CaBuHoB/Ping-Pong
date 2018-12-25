package com.suai.pingpong.game.controller;

import com.suai.pingpong.game.model.GameModel;
import com.suai.pingpong.game.model.ModelRoom;
import com.suai.pingpong.game.model.Room;
import com.suai.pingpong.login.model.User;
import com.suai.pingpong.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class RoomsController {

    private final UserService userService;

    @Autowired
    public RoomsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/rooms")
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("rooms.html");
        ModelRoom modelRoom = ModelRoom.getInstance();
        modelAndView.addObject("rooms", modelRoom.list());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        modelAndView.addObject("username", user.getUsername());
        return modelAndView;
    }

    @GetMapping(value = "/listRooms")
    @ResponseBody
    public List<Room> getRooms() {
        ModelRoom modelRoom = ModelRoom.getInstance();
        return modelRoom.getModelRoomList();
    }

    @PostMapping(value = "/rooms")
    public String create() {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        String owner = user.getUsername();

        Room room = new Room();
        room.setOwner(owner);

        ModelRoom modelRoom = ModelRoom.getInstance();
        if (!modelRoom.list().contains(owner)) {
            modelRoom.add(room);
        }

        modelAndView.addObject("owner", owner);
        modelAndView.setViewName("userRoom.html");
        return "redirect:/rooms/" + owner;
    }

    @GetMapping(value = "/rooms/{owner}")
    public ModelAndView openRoom(@PathVariable String owner) {
        ModelAndView modelAndView = new ModelAndView("userRoom.html");
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelRoom modelRoom = ModelRoom.getInstance();
        if (!modelRoom.list().contains(owner)) {
            modelAndView.setViewName("../static/error/404.html");
            return modelAndView;
        } else if (!owner.equals(user)) {
            List<Room> rooms = modelRoom.getModelRoomList();
            for (Room roomFromList : rooms) {
                if (roomFromList.getOwner().equals(owner))
                    roomFromList.setNumberOfUsers(2);
            }
        }
        modelAndView.addObject("owner", owner);
        modelAndView.addObject("username", user);
        return modelAndView;
    }

    @MessageMapping("/roomSocket/{owner}")
    @SendTo("/topic/roomSocket/{owner}")
    GameModel sendCoordinate(GameModel coordinates) {
        return coordinates;
    }

}
