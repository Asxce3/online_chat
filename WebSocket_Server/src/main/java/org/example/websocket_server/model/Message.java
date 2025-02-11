package org.example.websocket_server.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {
    private int id;
    private int userId;
    private int roomId;
    private String text;
    private LocalDateTime dateMessage = LocalDateTime.now();

}
