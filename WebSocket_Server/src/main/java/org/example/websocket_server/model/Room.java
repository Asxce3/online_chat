package org.example.websocket_server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room {
    private int id;
    private int roomId;
    private String roomName;
    private String sessionId;
    private String username;
    private boolean active;

}
