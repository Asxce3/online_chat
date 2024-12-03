package org.example.websocket_server.dao;

import org.example.websocket_server.model.Message;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class MessageDAO {

    private final JdbcTemplate jdbcTemplate;

    public MessageDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void createMessage(Message message) {
        try {
            String insertMessageSql = "INSERT INTO Message VALUES (default, ?, ?, ?) RETURNING id";
            Integer messageId =  jdbcTemplate.queryForObject(insertMessageSql, new Object[]{
                    message.getUserId(),
                            message.getText(),
                            message.getDateMessage()
            }, Integer.class);


            jdbcTemplate.update("INSERT INTO Room_Message VALUES (?, ?)",
                    message.getRoomId(),
                    messageId
            );

        }   catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
