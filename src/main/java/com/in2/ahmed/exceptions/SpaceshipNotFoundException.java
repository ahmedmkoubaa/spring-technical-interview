package com.in2.ahmed.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpaceshipNotFoundException extends RuntimeException {

    public SpaceshipNotFoundException(String message) {
        super(message);
    }

    public SpaceshipNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
