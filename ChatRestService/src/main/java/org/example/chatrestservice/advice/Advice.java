package org.example.chatrestservice.advice;

import org.example.chatrestservice.exception.LongRoomName;
import org.example.chatrestservice.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Advice {

    @ExceptionHandler(LongRoomName.class)
    public ResponseEntity<Response> longRoomName(LongRoomName e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
