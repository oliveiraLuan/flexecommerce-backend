package com.flexautopecas.flexecommerce.resources.exceptions;

import com.flexautopecas.flexecommerce.services.exceptions.DatabaseException;
import com.flexautopecas.flexecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest servlet){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), servlet.getRequestURI(), e.getMessage(), "Resource not found"));
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest servletRequest){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), servletRequest.getRequestURI(), e.getMessage(), "Database violation"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validateException(MethodArgumentNotValidException e, HttpServletRequest servletRequest){
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new StandardError(Instant.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), servletRequest.getRequestURI(), e.getMessage(), "Validate exception"));
    }
}
