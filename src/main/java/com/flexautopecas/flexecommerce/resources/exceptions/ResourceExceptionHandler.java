package com.flexautopecas.flexecommerce.resources.exceptions;

import com.flexautopecas.flexecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
