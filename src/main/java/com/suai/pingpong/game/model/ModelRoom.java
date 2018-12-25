package com.suai.pingpong.game.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class ModelRoom {
    @Getter
    private static final ModelRoom instance = new ModelRoom();
    @Getter
    private final List<Room> modelRoomList;

    private ModelRoom() {
        modelRoomList = new ArrayList<>();
    }

    public void add(Room room) {
        modelRoomList.add(room);
    }

    public List<String> list() {
        return modelRoomList.stream()
                .map(Room::getOwner)
                .collect(Collectors.toList());
    }

    public void deleteRoom(String owner) {
        for (Room room : modelRoomList) {
            if (room.getOwner().equals(owner)) {
                modelRoomList.remove(room);
                break;
            }
        }
    }

    public static void setNumberOfUsers(String owner, int number) {
        for (Room roomFromList : instance.modelRoomList) {
            if (roomFromList.getOwner().equals(owner)) {
                roomFromList.setNumberOfUsers(number);
            }
        }
    }
}
