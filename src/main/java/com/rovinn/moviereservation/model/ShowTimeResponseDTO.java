package com.rovinn.moviereservation.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowTimeResponseDTO {
    private Long id;
    private Long movieId;
    private String hallName;
    private LocalDateTime timeStart;
    private Integer totalSeats;
    private Integer availableSeats;
}
