package org.example.websocket_server.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.websocket_server.dao.RoomDAO;
import org.example.websocket_server.model.Message;
import org.example.websocket_server.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.text.MessageFormat;
import java.util.*;

@Component
public class ChatHandler extends TextWebSocketHandler {

    private RoomDAO roomDAO;
    private String username;
    private final Map<Integer, List<WebSocketSession>> mapOfSessions = new HashMap<>();

    public ChatHandler(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Message requestMessage = new ObjectMapper().readValue(message.getPayload(), Message.class);
        int roomId = requestMessage.getRoomId();

        List<WebSocketSession> lst = mapOfSessions.get(roomId);
        for (WebSocketSession wss: lst) {
            if (wss == session) {
                continue;
            }
            String response = MessageFormat.format("{0} : {1}", requestMessage.getSenderName(), requestMessage.getText());
            wss.sendMessage(new TextMessage(response));
        }
    }

    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        username = session.getAttributes().get("username").toString();
        Optional<Room> optRoom = roomDAO.getRoomIdByUsername(username);

        if (optRoom.isEmpty()) {
            throw new RuntimeException("user not found");
        }
        Room room = optRoom.get();
        int roomId = room.getRoomId();
        roomDAO.insertSessionIdInRoom(session.getId(), username);

        if (mapOfSessions.containsKey(roomId)) {
            mapOfSessions.get(roomId).add(session);
        }   else {
            List<WebSocketSession> newListOfSessionForRoomId = new ArrayList<>();
            newListOfSessionForRoomId.add(session);
            mapOfSessions.put(room.getRoomId(), newListOfSessionForRoomId);

        }

    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        username = session.getAttributes().get("username").toString();
        Optional<Room> optRoom = roomDAO.getRoomIdByUsername(username);
        Room room = optRoom.get();
        int roomId = room.getRoomId();
        mapOfSessions.get(roomId).remove(session);
        System.out.println(mapOfSessions);
        roomDAO.clearSessionId(session.getId());
    }
}

// Отрефакторить
// Сделать сессии один ко многим (доп таблица с сессиями)
// Брать имя от верефецированных пользователей (jwt или db)
// Реализовать сохранение сообщений в базе
// Доабвить иссторию сообщений с пагенация с проверкой на пользователя

// Доступ к ресурсу через nginx -- на свое усмотрению 
