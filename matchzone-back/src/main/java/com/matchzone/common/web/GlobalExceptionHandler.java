package com.matchzone.common.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Maneja de forma global las excepciones lanzadas por los controladores.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatus(ResponseStatusException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
        Map<String, Object> body = Map.of(
            "timestamp", LocalDateTime.now(),
            "status",    status.value(),
            "error",     status.getReasonPhrase(),
            "message",   ex.getReason()
        );
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UsernameNotFoundException ex) {
        Map<String,Object> body = Map.of(
            "timestamp", LocalDateTime.now(),
            "status",    HttpStatus.NOT_FOUND.value(),
            "error",     "Not Found",
            "message",   ex.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        Map<String,Object> body = Map.of(
            "timestamp", LocalDateTime.now(),
            "status",    HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "error",     "Internal Server Error",
            "message",   "Ocurrió un error inesperado"
        );
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}