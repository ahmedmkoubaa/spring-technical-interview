package com.in2.ahmed.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SpaceshipAlreadyExistsException extends RuntimeException {

    public SpaceshipAlreadyExistsException(String message) {
        super(message);
    }
}
