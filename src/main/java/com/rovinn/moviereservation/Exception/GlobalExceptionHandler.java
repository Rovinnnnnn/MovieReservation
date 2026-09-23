package com.rovinn.moviereservation.Exception;

import com.rovinn.moviereservation.model.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFound(ResourceNotFoundException e) {
         ErrorResponseDTO error = new ErrorResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value());
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(DuplicationResourceException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(DuplicationResourceException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(e.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
