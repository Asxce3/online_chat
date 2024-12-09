package org.example.chatrestservice.service;

import org.example.chatrestservice.dao.MessageDAO;
import org.example.chatrestservice.dao.RoomDAO;
import org.example.chatrestservice.model.Message;
import org.example.chatrestservice.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomDAO roomDAO;
    private final MessageDAO messageDAO;

    public RoomService(RoomDAO roomDAO, MessageDAO messageDAO) {
        this.roomDAO = roomDAO;
        this.messageDAO = messageDAO;
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

    public void createRoom(Room room) {
        roomDAO.createRoom(room);
    }

    public List<Message> getMessages(int roomId, int messageId) {
        if (messageId == 0) {
            messageId = messageDAO.getQuantityOfMessages(roomId) + 1;
            System.out.println(messageId);
        }
        return messageDAO.getMessages(roomId, messageId);
    }

}