package org.example.websocket_server.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.websocket_server.model.Message;
import org.example.websocket_server.service.ChatService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.text.MessageFormat;
import java.util.*;

@Component
public class ChatHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessions = new ArrayList<>();
    private final ChatService chatService;

    public ChatHandler(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void handleTextMessage(WebSocketSession currentSession, TextMessage message) throws Exception {
        Message requestMessage = new ObjectMapper().readValue(message.getPayload(), Message.class);

        int roomId  = requestMessage.getRoomId();
        int userId = requestMessage.getUserId();
        chatService.createRoomUser(roomId, userId);

        List<String> sessionsInRoom = chatService.getUserSessionsByRoomId(roomId);

        for (WebSocketSession wss: sessions){
            if (wss.equals(currentSession)) {
                continue;
            }

            if(sessionsInRoom.contains(wss.getId())) {
                String response = MessageFormat.format("{0} : {1}",
                        currentSession.getAttributes().get("username").toString(),
                        requestMessage.getText());
                wss.sendMessage(new TextMessage(response));
            }
        }
        chatService.createMessage(requestMessage);
    }

    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        int userId = Integer.parseInt(session.getAttributes().get("user_id").toString());
        chatService.createUserSession(userId, session.getId());
        sessions.add(session);

    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        chatService.deleteUserSession(session.getId());
        sessions.remove(session);
    }
}

// Отрефакторить
// Сделать сессии один ко многим (доп таблица с сессиями)
// Брать имя от верефецированных пользователей (jwt или db)
// Реализовать сохранение сообщений в базе
// Доабвить иссторию сообщений с пагенация с проверкой на пользователя

// Доступ к ресурсу через nginx -- на свое усмотрению 
