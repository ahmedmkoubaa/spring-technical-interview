package com.in2.ahmed.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when attempting to create a spaceship that already exists.
 *
 * <p>
 * This exception is thrown when a request is made to create a new spaceship, but a spaceship with the same ID already exists.
 * It is annotated with {@link ResponseStatus} to return an HTTP 409 Conflict status code to the client.
 * </p>
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class SpaceshipAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new SpaceshipAlreadyExistsException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public SpaceshipAlreadyExistsException(String message) {
        super(message);
    }
}
