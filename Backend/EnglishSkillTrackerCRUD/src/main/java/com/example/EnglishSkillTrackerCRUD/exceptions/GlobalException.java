package com.example.EnglishSkillTrackerCRUD.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?>handlerResourceNotFound(ResourceNotFoundException resourceNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundException.getMessage());

    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error: " + exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExeptionManager(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno en el servidor: " + exception.getClass().getSimpleName() + " - " + exception.getMessage());
    }
}
