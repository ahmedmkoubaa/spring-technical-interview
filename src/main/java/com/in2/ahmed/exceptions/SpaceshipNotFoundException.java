package com.in2.ahmed.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class to represent a scenario where a spaceship is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpaceshipNotFoundException extends RuntimeException {

    /**
     * Constructs a new SpaceshipNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public SpaceshipNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new SpaceshipNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   the cause (which is saved for later retrieval by the getCause() method)
     */
    public SpaceshipNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
