package org.example.websocket_server.service;

import org.example.websocket_server.dao.MessageDAO;
import org.example.websocket_server.dao.RoomUserDAO;
import org.example.websocket_server.dao.UserSessionDAO;
import org.example.websocket_server.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChatService {

    private final UserSessionDAO userSessionDAO;
    private final MessageDAO messageDAO;
    private final RoomUserDAO roomUserDAO;

    public ChatService(UserSessionDAO userSessionDAO, MessageDAO messageDAO, RoomUserDAO roomUserDAO) {
        this.userSessionDAO = userSessionDAO;
        this.messageDAO = messageDAO;
        this.roomUserDAO = roomUserDAO;
    }

    public void createMessage(Message message) {
        messageDAO.createMessage(message);
    }

    public List<String> getUserSessionsByRoomId(int roomId) {
        return userSessionDAO.getUserSessionsByRoomId(roomId);
    }

    public void createUserSession(int userId, String sessionId) {
        userSessionDAO.createUserSession(userId, sessionId);
    }

    public void deleteUserSession(String sessionId) {
        userSessionDAO.deleteUserSession(sessionId);
    }

    public void createRoomUser(int roomId, int userId) {
        if(roomUserDAO.getRoom(roomId, userId).isEmpty()) {
            roomUserDAO.create(roomId, userId);
        }
    }
}
