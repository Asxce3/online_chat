package org.example.websocket_server.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private String text;
    private String senderName;
    private LocalDateTime dateMessage;
    private int roomId;

}
