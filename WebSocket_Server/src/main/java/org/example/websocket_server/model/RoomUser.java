package org.example.websocket_server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoomUser {
    private int roomId;
    private int userId;
}
