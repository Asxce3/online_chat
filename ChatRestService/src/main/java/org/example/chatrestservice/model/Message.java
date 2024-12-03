package org.example.chatrestservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Message {
    private int id;
    private int userId;
    private int roomId;
    private String text;
    private LocalDateTime dateMessage = LocalDateTime.now();

}