package org.example.chatrestservice.exception;

public class LongRoomName extends RuntimeException{
    public LongRoomName(String message) {
        super(message);
    }
}
