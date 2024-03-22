package com.in2.ahmed.handlers;

import com.in2.ahmed.exceptions.SpaceshipAlreadyExistsException;
import com.in2.ahmed.exceptions.SpaceshipNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Global exception handler class to handle custom and general exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the SpaceshipAlreadyExistsException and returns an appropriate HTTP response with a conflict status code.
     *
     * @param ex the exception to handle
     * @return a ResponseEntity with the conflict status code and the exception message
     */
    @ExceptionHandler(SpaceshipAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<String> handleSpaceshipAlreadyExistsException(SpaceshipAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    /**
     * Handles the SpaceshipNotFoundException and returns an appropriate HTTP response with a not found status code.
     *
     * @param ex the exception to handle
     * @return a ResponseEntity with the not found status code and the exception message
     */
    @ExceptionHandler(SpaceshipNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleSpaceshipNotFoundException(SpaceshipNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles all other exceptions and returns an internal server error status code with a generic error message.
     *
     * @param ex the exception to handle
     * @return a ResponseEntity with the internal server error status code and a generic error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Return a generic error message to the client
        String errorMessage = "An unexpected error occurred. Please try again later: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
