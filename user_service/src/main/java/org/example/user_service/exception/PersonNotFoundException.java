package org.example.user_service.exception;

import java.util.NoSuchElementException;

public class PersonNotFoundException extends NoSuchElementException {
    public PersonNotFoundException(String message) {
        super(message);
    }
}
