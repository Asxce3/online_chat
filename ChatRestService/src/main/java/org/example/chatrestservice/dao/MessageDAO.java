package org.example.chatrestservice.dao;

import org.example.chatrestservice.model.Message;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDAO {
    private final JdbcTemplate jdbcTemplate;

    public MessageDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Message> getMessages(int roomId, int messageId) {
        int limit = 3;

        String getMessagesByRoom =
                """
                SELECT *
                FROM Message
                JOIN Room_Message
                ON message.id = Room_Message.message_id
                WHERE room_id = ?
                ORDER BY id DESC
                OFFSET ?
                LIMIT ?;
                """;

        try {
            return jdbcTemplate.query(
                    getMessagesByRoom,
                    new Object[]{roomId, messageId, limit},
                    new BeanPropertyRowMapper<>(Message.class)
            );
        }   catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
