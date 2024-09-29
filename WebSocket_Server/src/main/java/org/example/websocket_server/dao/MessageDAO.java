package org.example.websocket_server.dao;

import org.example.websocket_server.model.Message;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDAO {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Message> getMessages() {
        try {
            return jdbcTemplate.query("SELECT * FROM Message", new BeanPropertyRowMapper<>(Message.class));
        }   catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
