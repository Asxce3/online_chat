package org.example.chatrestservice.service;

import org.example.chatrestservice.dao.RoomDAO;
import org.example.chatrestservice.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomDAO roomDAO;

    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public List<Room> getRooms() {
        return roomDAO.getRooms();
    }

    public Room getRoom (int id) {
        Optional<Room> optRoom = roomDAO.getRoom(id);
        if(optRoom.isEmpty()) {
            throw new RuntimeException("Room not found");
        }
        return optRoom.get();
    }

    public void createRoom(Room room) {
        roomDAO.createRoom(room);
    }

}