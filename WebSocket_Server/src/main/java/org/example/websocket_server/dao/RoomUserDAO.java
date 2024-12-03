package org.example.websocket_server.dao;

import org.example.websocket_server.model.RoomUser;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoomUserDAO {

    private final JdbcTemplate jdbcTemplate;

    public RoomUserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(int roomId, int userId) {
        try {
            jdbcTemplate.update("INSERT INTO Room_Person VALUES (?, ?)", roomId, userId);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<RoomUser> getRoom(int roomId, int userId) {
        try {
            return jdbcTemplate.query("SELECT * FROM Room_Person WHERE room_id = ? AND user_id = ?",
                    new Object[]{roomId, userId},
                    new BeanPropertyRowMapper<>(RoomUser.class)).stream().findAny();

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
