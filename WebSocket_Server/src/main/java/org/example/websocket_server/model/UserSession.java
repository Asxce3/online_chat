package org.example.websocket_server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSession {
    private int id;
    private String userId;
    private String sessionId;

    public String toString() {
        return id + " " + userId + " " + sessionId ;
    }
}
