package org.example.websocket_server.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserSessionDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserSessionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUserSession(int userId, String sessionId){
        try {
            jdbcTemplate.update("INSERT INTO Person_session VALUES(default, ?, ?)",
                    userId,
                    sessionId);
        }   catch (DataAccessException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteUserSession(String sessionId){
        try {
            jdbcTemplate.update("DELETE FROM Person_session WHERE session_id = ?", sessionId);
        }   catch (DataAccessException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<String> getUserSessionsByRoomId(int roomId) {
        try {
            String query = """ 
                    SELECT session_id
                    FROM Room_Person
                    JOIN Person_session
                    ON Room_Person.user_id = Person_session.user_id
                    WHERE Room_Person.room_id = ?;
                """;
            return jdbcTemplate.queryForList(query, new Object[]{roomId}, String.class);
        }   catch (DataAccessException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
