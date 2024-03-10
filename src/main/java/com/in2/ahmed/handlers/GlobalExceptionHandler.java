package com.in2.ahmed.handlers;

import com.in2.ahmed.exceptions.SpaceshipAlreadyExistsException;
import com.in2.ahmed.exceptions.SpaceshipNotFoundException;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SpaceshipAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<String> handleSpaceshipAlreadyExistsException(SpaceshipAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(SpaceshipNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleSpaceshipNotFoundException(SpaceshipNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {

        // Return a generic error message to the client
        String errorMessage = "An unexpected error occurred. Please try again later: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
