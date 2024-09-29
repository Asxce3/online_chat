package org.example.websocket_server.dao;

import org.example.websocket_server.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Room> getRoomIdByUsername(String username) {
        return jdbcTemplate.query("SELECT * FROM chat_room WHERE username = ?",
                new Object[]{username},
                new BeanPropertyRowMapper<>(Room.class)).stream().findAny();
    }

    public void insertSessionIdInRoom(String sessionId, String username) {
        jdbcTemplate.update("UPDATE chat_room SET session_id = ?, active = ? WHERE username = ?",
                sessionId, true ,username);
    }

    public List<Room> getRoomsByRoomId(int roomId) {
        return jdbcTemplate.query("SELECT * FROM chat_room WHERE room_id = ?",
                new Object[]{roomId},
                new BeanPropertyRowMapper<>(Room.class));
    }

    public void clearSessionId(String sessionId) {
        jdbcTemplate.update("UPDATE chat_room SET session_id = ?, active = ? WHERE session_id = ?",
                null,
                false,
                sessionId);
    }


}
