package com.myproject.customer_mang_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        ResponseException exception = new ResponseException(
                entityNotFoundException.getMessage(),
                entityNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {EntityAlreadyExistsException.class})
    public ResponseEntity<Object> handleEntityAlreadyExistsException(EntityAlreadyExistsException entityAlreadyExistsException){
        ResponseException exception = new ResponseException(
                entityAlreadyExistsException.getMessage(),
                entityAlreadyExistsException.getCause(),
                HttpStatus.CONFLICT
        );
        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }
}
