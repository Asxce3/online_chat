package org.example.chatrestservice.service;

import org.example.chatrestservice.dao.MessageDAO;
import org.example.chatrestservice.dao.RoomDAO;
import org.example.chatrestservice.dao.RoomUserDAO;
import org.example.chatrestservice.model.Message;
import org.example.chatrestservice.model.Room;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomDAO roomDAO;
    private final MessageDAO messageDAO;
    private final RoomUserDAO roomUserDAO;

    public RoomService(RoomDAO roomDAO, MessageDAO messageDAO, RoomUserDAO roomUserDAO) {
        this.roomDAO = roomDAO;
        this.messageDAO = messageDAO;
        this.roomUserDAO = roomUserDAO;
    }

    public List<Room> getRooms(String userId) {
        return roomDAO.getRooms(userId);
    }

    public Room getRoom (int id) {
        Optional<Room> optRoom = roomDAO.getRoom(id);
        if(optRoom.isEmpty()) {
            throw new RuntimeException("Room not found");
        }
        return optRoom.get();
    }
    @Transactional
    public void createRoom(Room room, int userId) {
        int roomId = roomDAO.createRoom(room);
        roomUserDAO.create(roomId, userId);

    }

    public List<Message> getMessages(int roomId, int messageId) {

        return messageDAO.getMessages(roomId, messageId * 3);
    }

}