package org.example.chatrestservice.dao;

import org.example.chatrestservice.model.Room;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomDAO {
    private final JdbcTemplate jdbcTemplate;

    public RoomDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Room> getRooms() {
        return jdbcTemplate.query("SELECT * FROM chat_room", new BeanPropertyRowMapper<>(Room.class));
    }

    public Optional<Room> getRoom(int id) {
        return jdbcTemplate.query("SELECT * FROM chat_room WHERE id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Room.class)).stream().findAny();
    }

    public void createRoom(Room room) {
        jdbcTemplate.update("INSERT INTO chat_room VALUES (default, ?, ?)",
                room.getRoomName(),
                room.getUsername());
    }
}
