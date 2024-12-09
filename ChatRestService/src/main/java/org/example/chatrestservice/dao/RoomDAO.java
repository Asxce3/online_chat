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

    public List<Room> getRooms(String userId) {
        String query = """
                SELECT id, room_name 
                FROM room_person 
                JOIN Room 
                ON room_person.room_id = Room.id 
                WHERE user_id = ?
                """;
        return jdbcTemplate.query(query,
                new Object[] {Integer.parseInt(userId)},
                new BeanPropertyRowMapper<>(Room.class));
    }

    public Optional<Room> getRoom(int id) {
        return jdbcTemplate.query("SELECT * FROM Room WHERE id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Room.class)).stream().findAny();
    }

    public void createRoom(Room room) {
        jdbcTemplate.update("INSERT INTO Room VALUES (default, ?)", room.getRoomName());
    }
}
