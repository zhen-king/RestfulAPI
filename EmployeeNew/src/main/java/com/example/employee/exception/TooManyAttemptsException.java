package com.example.employee.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class TooManyAttemptsException extends JsonProcessingException {
    protected TooManyAttemptsException(String msg) {
        super("Too Many Attempts, try again later");
    }
}
