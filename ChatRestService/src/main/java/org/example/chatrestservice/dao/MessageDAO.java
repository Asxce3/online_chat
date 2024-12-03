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

    public int getQuantityOfMessages(int roomId) {
        String query =  """
                    SELECT message_id
                    FROM Message
                    JOIN Room_Message
                    ON message.id = Room_Message.message_id
                    WHERE room_id = ? ORDER BY id DESC
                    LIMIT 1;
                """;
        return jdbcTemplate.queryForObject(query, new Object[]{roomId}, Integer.class);

    }

    public List<Message> getMessages(int roomId, int messageId) {
        int limit = 3;

        String getMessagesByRoom =
                """
                SELECT *
                FROM Message
                JOIN Room_Message
                ON message.id = Room_Message.message_id
                WHERE room_id = ? AND message_id < ?
                ORDER BY id DESC
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
