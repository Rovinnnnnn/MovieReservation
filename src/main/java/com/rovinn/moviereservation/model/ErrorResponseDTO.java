package com.rovinn.moviereservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    private String message;
    private int Status;
    private LocalDateTime timestamp;
    public ErrorResponseDTO(String message, int Status) {
        this.message = message;
        this.Status = Status;
    }
}
