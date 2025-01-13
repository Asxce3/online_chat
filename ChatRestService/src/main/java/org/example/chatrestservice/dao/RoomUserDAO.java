package org.example.chatrestservice.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
