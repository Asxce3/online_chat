package org.example.chatrestservice.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.chatrestservice.model.Message;
import org.example.chatrestservice.model.Room;
import org.example.chatrestservice.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getRooms(){
        return roomService.getRooms();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable int id) {
        return roomService.getRoom(id);
    }

    @PostMapping
    public void createRoom(@RequestBody Room room) {
        roomService.createRoom(room);
    }

    @GetMapping("/{roomId}/")
    public List<Message> getMessagesByRoomId(@PathVariable int roomId,
                                             @RequestParam int messageId,
                                             HttpServletResponse response) {
        response.setHeader("message_id", messageId + "");

        return roomService.getMessages(roomId, messageId);
    }

}
