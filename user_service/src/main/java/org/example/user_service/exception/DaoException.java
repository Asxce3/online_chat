package org.example.user_service.exception;

import org.springframework.dao.DataAccessException;

public class DaoException extends DataAccessException {
    public DaoException(String message) {
        super(message);
    }
}
